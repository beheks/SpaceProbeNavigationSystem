package xyz.felipearaujo.spaceprobenavigationsystem.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import rx.Subscriber;

public class ActivitySubscriber<T> extends Subscriber<T> {

  private AppCompatActivity mActivity;

  public ActivitySubscriber(AppCompatActivity activity) {
    super();
    mActivity = activity;
  }

  @Override
  public void onCompleted() {
  }

  @Override
  public void onError(Throwable e) {
    Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
    Log.e("ActivitySubscriber", e.getMessage());
  }

  @Override
  public void onNext(T t) {
  }
}
