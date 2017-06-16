# Projekt ausführen
## Datenbank
Zum Ausführen des Projekts ist eine lokale MySQL Datenbank notwendig. Das Schema wird beim ersten Verbinden automatisch erstellt. Dazu muss auf der MySQL Datenbank ein Adminbenutzer "root" konfiguriert sein mit dem entsprechenden Passwort "root". Der Connectionstring ist in der Datei hibernate.cfg.xml hinterlegt:

```xml
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/mhcpms?zeroDateTimeBehavior=convertToNull&amp;createDatabaseIfNotExist=true</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">root</property>
```

# Team Projekt Software Engineering FS 2017 (Gruppe Rot)

## Used File Formats and Programs
| File extension | Program name    | type              | link                                                                                                                                                                            |
|----------------|-----------------|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| vsdx           | Microsoft Visio | free for students | https://intranet.bfh.ch/BFH/de/Dienste/IT/Verguenstigungen_Angebote/Verguenstigungen/Seiten/default.aspx#ctl00_SPWebPartManager1_g_9ff6590b_5549_479a_be31_3d8a60dc8827_ctl00_1 |
| dia            | Dia             | Open Source       | http://dia-installer.de/download/index.html                                                                                                                                     |
# Implementation mit MVP

## Packages
Anstelle die Klassen nach technischen Aspekten (Presenter, View, ViewModel) zu trennen befinden sich nun alle Klassen, die Gemeinsam
einen Anwendungsfall abdecken in einem gemeinsamen Package. Das hat den Vorteil, dass alles was zusammen gehört sich an gleicher Stelle finden lässt und die Anzahl Packages und damit die Übersichtlich lässt sich so verbessern.

### Beispiel Benutzerverwaltung - ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users
* UserDetailPresenter
* UserDetailView
* UserEditViewModel
* UserManagementPresenter
* UserManagementView

Davon ausgenommen sind aktuell die Domainobjekte und Services die sich unter ch.bfh.bti7081.s2017.red.mhc_pms.domain respektive ch.bfh.bti7081.s2017.red.mhc_pms.services befinden. 

## Views
Eine View ist dafür verantwortlich, die graphischen Benutzerelemente korrekt anzuzeigen. Die View ist selber verantwortlich für die Navigation zu anderen Views da der Presenter vom konkrete UX Design nichts wissen muss. Eine View erhält daher eine Instanz vom Navigator Konstruktor. Aufgrund der bidirektionalen Verbindung zwischen View und Presenter kann der Presenter nicht im Konstruktor der View übergeben werden. Stattdessen muss jede View das Interface ViewBase implementieren, welches eine Methode setPresenter zur Verfügung stellt. Eine Defaultimplementation ist in der Klasse MainPageContent vorhanden. Im Konstruktor der View darf also nicht auf den Presenter zugegriffen werden.

```Java
    @Override
    public void setPresenter(UserDetailPresenter presenter) {
        this.presenter = presenter;
        presenter.onInitialize();
    }
```

Eine View selber weiss nichts von den Services. Diese sind lediglich dem Presenter bekannt. Der Presenter wird mit den entsprechenden Services ausgestattet der View übergeben.

### ViewInjector
In Anlehnung an das Dependency Injection Pattern wurde die 'UserSession' nach ViewInjector umbenannt, weil dieser die Views eben 'injected' mit den notwendigen Abhängigkeiten:

```Java
public MainPage getMainPage() {
        if (mMainPage == null) {
            mMainPage = new MainPage(mNavigator);
            mMainPage.setPresenter(new MainPagePresenter(mMainPage, this));
        }
        return mMainPage;
    }
```
Die Abhängigkeiten der ViewInjector Klasse werden in der ViewInjectorFactory erfüllt:

```Java
public class ViewInjectorFactory {

    /**
     * Creates a new ViewInjector object.
     *
     * @param ui
     * @param vaadinRequest
     * @return the view injector
     */
    public static ViewInjector createViewInjector(UI ui, VaadinRequest vaadinRequest) {
        ViewInjectorImpl r = new ViewInjectorImpl(ui, vaadinRequest);
        
        r.setUserService(new InMemoyUserService());
        r.setPasswordService(new Sha1PasswordService());
        r.setBillingService(new BillingService());
        
        return r;
    }
}
```
## Presenter
Der Presenter ist die Schnittstelle zwischen Views und BusinessLogik. Alle Benutzerinteraktionen bis auf die Navigation werden dem Presenter weitergegeben. Der Presenter kennt die View und kann indirekt deren Daten manipulieren. Jeder Presenter erbt von der Klasse PresenterBase<TView>.

Alle Service- und sonstige Abhängigkeiten müssen im Konstruktor der Presenters deklariert werden. Würde der Presenter für jede Abhängigkeit einen Setter haben, ist die wahrscheinlichkeit gross, dass vergessen wird dieses Property beim initialieren des Presenters zu setzen. Die Variante mit dem Konstruktor garantiert, dass keine Abhängigkeit vergessen wird.

```Java
public class UserDetailPresenter extends PresenterBase<UserDetailView> {
    private final PasswordService passwordService;
    private final UserService userService;

    public UserDetailPresenter(UserDetailView view, UserService userService, PasswordService passwordService) {
        super(view);
        this.userService = userService;
        this.passwordService = passwordService;
    }
    
    // ...
}
```

Die folgende Variante via Setter führt schnell zu eine NullReferenceException (insbesondere wenn ein Presenter mit einem Service erweitert wird):

```Java
public class UserDetailPresenter extends PresenterBase<UserDetailView> {
    private PasswordService passwordService;
    private UserService userService;

    public void setPasswordService(PasswordService passwordService){
        this.passwordService = passwordService;
    }
    
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    
    public UserDetailPresenter(UserDetailView view) {
        super(view);
    }
    
    // ...
}
```
Fehleranfälliges injecten der View:

```Java
public class ViewInjectorImpl implements ViewInjector {
    // ...
    
    public UserDetailView getUserDetailView() {
        if (mUserDetailView == null) {
            mUserDetailView = new UserDetailView(mNavigator);
            UserDetailPresenter presenter = new UserDetailPresenter(mUserDetailView);
            presenter.setUserService(mUserService);

            // Ooops, forgot to set PasswordService
        }

        return mUserDetailView;
    }
    
    // ...
}
```
