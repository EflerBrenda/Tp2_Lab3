package com.ulp.loginconfiles.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.ulp.loginconfiles.modelo.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    private static File directorio;
    private static File archivo;

    private static File conectar(Context context) {

        if (directorio == null) {
            directorio = context.getFilesDir();
            archivo = new File(directorio, "configuracion.txt");
            if (!archivo.exists()) {
                try {
                    archivo.createNewFile();
                } catch (IOException e) {
                    Log.d("error", e.toString());
                }
            }

        }
            return archivo;
        }

    public static void guardar(Context context, Usuario usuario)  {

        try{
            File a= conectar(context);
        FileOutputStream fo=new FileOutputStream(a);
        BufferedOutputStream bo= new BufferedOutputStream(fo);
        ObjectOutputStream oos=new ObjectOutputStream(bo);
        oos.writeObject(usuario);
        bo.flush();
        fo.close();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
            Log.d("salida",e.getMessage());
    }catch (IOException io){
        io.printStackTrace();
        Log.d("salida",io.getMessage());
    }

    }
    public static Usuario leer(Context context){

        Usuario usuario =null;
        try{
            File a= conectar(context);
            FileInputStream fi=new FileInputStream(a);
            BufferedInputStream bi= new BufferedInputStream(fi);
            ObjectInputStream ois=new ObjectInputStream(bi);
            usuario = (Usuario) ois.readObject();
            fi.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IOException io){
            io.printStackTrace();
        }
        return usuario;
    }

    public static Usuario login(Context context, String mail, String pass){

        Usuario u = null;
        Boolean loginOk= false;

            /*File a= conectar(context);
            FileInputStream fi=new FileInputStream(a);
            ObjectInputStream ois=new ObjectInputStream(fi);
            Usuario usuario = (Usuario) ois.readObject();
            fi.close();*/
            Usuario usuario=leer(context);
            if(usuario.getEmail().equals(mail) && usuario.getPassword().equals(pass)){
                 u= new Usuario(usuario.getDni(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getPassword());
                loginOk=true;
            }

        if(loginOk){
            return u;
        }else
        {
            return null;
        }
    }
}
