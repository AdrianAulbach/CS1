package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

/**
 * Base interface for all views that follow the MVP pattern.
 *
 * @author Samuel Egger
 * @param <TPresenter> the presenter type
 */
public interface MvpView<TPresenter> {

    /**
     * Sets the presenter for the view.
     *
     * @param presenter the presenter
     */
    public void setPresenter(TPresenter presenter);
}
