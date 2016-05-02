package com.jenzz.buildconstants.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @SuppressWarnings("unused")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Java constants
    String javaString = SampleBuildConstants.ASTRING;
    boolean javaBoolean = SampleBuildConstants.ABOOLEAN;
    int javaNumber = SampleBuildConstants.ANUMBER;

    // XML constants
    Resources res = getResources();
    String xmlString = res.getString(R.string.astring);
    boolean xmlBoolean = res.getBoolean(R.bool.aboolean);
    int xmlNumber = res.getInteger(R.integer.anumber);
  }
}
