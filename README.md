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

