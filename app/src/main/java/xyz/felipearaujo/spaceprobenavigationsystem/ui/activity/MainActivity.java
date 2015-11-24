package xyz.felipearaujo.spaceprobenavigationsystem.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import xyz.felipearaujo.spaceprobenavigationsystem.R;
import xyz.felipearaujo.spaceprobenavigationsystem.SpaceProbeNavigationSystem;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.DirectedPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.entity.Ship;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.component.DaggerActivityComponent;
import xyz.felipearaujo.spaceprobenavigationsystem.injector.module.ActivityModule;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.MoveShipToFinalPosition;
import xyz.felipearaujo.spaceprobenavigationsystem.interactor.SubmitData;


public class MainActivity extends AppCompatActivity {

  @Bind(R.id.ship_position) protected TextView mShipPosition;
  @Bind(R.id.spinner) protected ProgressBar mSpinner;
  @Bind(R.id.calculate_button) protected Button mCalculateButton;
  @Bind(R.id.submit_button) protected Button mSubmitButton;
  @Bind(R.id.email) protected EditText mEmail;

  @Inject MoveShipToFinalPosition mMoveAlienShipToFinalPosition;
  @Inject SubmitData mSubmitData;
  @Inject Ship mShip;

  @OnClick(R.id.calculate_button)
  protected void calculateButtonClick() {
    showSpinner();
    mCalculateButton.setEnabled(false);

    mMoveAlienShipToFinalPosition.execute(mEmail.getText().toString())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new ActivitySubscriber<DirectedPosition>(MainActivity.this) {
          @Override
          public void onCompleted() {
            super.onCompleted();

            showSubmitButton();
            showText();
          }

          @Override
          public void onError(Throwable e) {
            super.onError(e);

            showCalculateButton();
            showText();
          }
        });
  }

  @OnClick(R.id.submit_button)
  protected void submitButtonClick() {
    showSpinner();
    mSubmitButton.setEnabled(false);

    mSubmitData.execute(mEmail.getText().toString())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new ActivitySubscriber<String>(MainActivity.this) {
          @Override
          public void onCompleted() {
            super.onCompleted();

            showCalculateButton();
            showText();
          }

          @Override
          public void onNext(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
          }

          @Override
          public void onError(Throwable e) {
            super.onError(e);

            showSubmitButton();
            showText();
          }
        });
  }

  @OnTextChanged(R.id.email)
  protected void emailTextChanged() {
    if(Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().subSequence(0, mEmail.length())).matches()) {
      mCalculateButton.setEnabled(true);
    }
    else {
      mCalculateButton.setEnabled(false);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DaggerActivityComponent
        .builder()
        .commonComponent(SpaceProbeNavigationSystem.getCommonComponent())
        .activityModule(new ActivityModule())
        .build()
        .inject(this);

    ButterKnife.bind(this);

    showText();
    showCalculateButton();
    mCalculateButton.setEnabled(false);
  }

  private void showCalculateButton() {
    mShip.resetShip();
    mEmail.setEnabled(true);
    mSubmitButton.setVisibility(View.GONE);
    mCalculateButton.setVisibility(View.VISIBLE);
    mCalculateButton.setEnabled(true);
  }

  private void showSubmitButton() {
    mEmail.setEnabled(false);
    mSubmitButton.setVisibility(View.VISIBLE);
    mCalculateButton.setVisibility(View.GONE);
    mSubmitButton.setEnabled(true);
  }

  private void showText() {
    mShipPosition.setText(mShip.toString());
    mShipPosition.setVisibility(View.VISIBLE);
    mSpinner.setVisibility(View.GONE);
  }

  private void showSpinner() {
    mShipPosition.setVisibility(View.GONE);
    mSpinner.setVisibility(View.VISIBLE);
  }


}
