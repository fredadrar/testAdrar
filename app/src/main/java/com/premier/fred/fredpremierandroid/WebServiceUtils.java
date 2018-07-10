package com.premier.fred.fredpremierandroid;

import android.os.SystemClock;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import dao.PostBean;
import dao.UserBean;

//Classe appartenant au model. Gérera les futurs requêtes

public class WebServiceUtils {

    private static String SEND_MESSAGE_URL = "http://192.168.0.10:8888/chatServeur/rest/WebService/sendPost";
    private static String SEND_USER_URL = "http://192.168.0.10:8888/chatServeur/rest/WebService/sendUser";
    private static String GET_MESSAGES_URL = "http://192.168.0.10:8888/chatServeur/rest/WebService/getPosts";
    private static String GET_USERS_URL = "http://192.168.0.10:8888/chatServeur/rest/WebService/getUsers";
    private static Gson gson = new Gson();

    public static EleveBean loadEleveFromWeb() {
        SystemClock.sleep(5000); //Attente de 5 secondes
        return new EleveBean("Toto", "Tata");
    }

    public static void sendMessage(PostBean message) throws Exception {
        String jsonMessage = gson.toJson(message);
        OkHttpUtils.sendPostOkHttpRequest(SEND_MESSAGE_URL, jsonMessage);
    }

    public static void sendUser(UserBean user) throws Exception {
        String jsonUser = gson.toJson(user);
        OkHttpUtils.sendPostOkHttpRequest(SEND_USER_URL, jsonUser);
    }

    public static ArrayList<PostBean> getMessages() throws Exception {
        String jsonMessages = OkHttpUtils.sendGetOkHttpRequest(GET_MESSAGES_URL);
        return gson.fromJson(jsonMessages, new TypeToken<ArrayList<PostBean>>() {
        }.getType());
    }

    public static ArrayList<UserBean> getUsers() throws Exception {
        String jsonUsers = OkHttpUtils.sendGetOkHttpRequest(GET_USERS_URL);
        return gson.fromJson(jsonUsers, new TypeToken<ArrayList<UserBean>>() {
        }.getType());
    }
}
