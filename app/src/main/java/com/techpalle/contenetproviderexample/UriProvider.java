package com.techpalle.contenetproviderexample;

import android.net.Uri;

/**
 * Created by DELL on 29-Jan-17.
 */
//This file will be  exposed to outside world
//URI - Uniform Resource Identifier
// Declare column names and prepare URIs for your table
public class UriProvider {
    public static final String _ID = "id"; //Integer
    public static final String NAME = "name"; //String
    public static final String SUB = "sub"; //String

    //Prepare URI for student information onfo
    public static final Uri STUDENT_URI = Uri.parse("content://com.techpalle.B_34/studentTable");

}
