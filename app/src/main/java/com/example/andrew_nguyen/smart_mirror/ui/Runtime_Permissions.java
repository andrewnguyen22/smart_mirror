//***********************************************
//Andrew Nguyen - Last Edit 5/20/17
//Runtime Permissions
//Asks user to grant camera/external storage perm
//***********************************************
package com.example.andrew_nguyen.smart_mirror.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.example.andrew_nguyen.smart_mirror.R;
import com.example.andrew_nguyen.smart_mirror.tools.Permissions;

/**
 * Created by andrewnguyen on 11/11/16.
 */

public class Runtime_Permissions extends Permissions {
    private static final int REQUEST_PERMISSION = 10;//Arbitrary Value
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Prompt user for runtime permissions
        requestAppPermissions(new String[] {
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                R.string.enable_perm,REQUEST_PERMISSION);



    }
    @Override
    public void onPermissionsGranted(int requestCode){
        //User -> Permissions Granted
        System.out.println("Permission Granted");
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}
