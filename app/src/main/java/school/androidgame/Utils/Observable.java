package school.androidgame.Utils;

import java.util.Observer;

/**
 * Created by kezab on 10.10.17.
 */

public interface Observable<T> {

    public abstract void Subscribe(Observer obs);

}
