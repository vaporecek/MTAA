package com.example.mathew.movies.RESTbackend;

import android.content.Intent;
import android.os.AsyncTask;


import com.example.mathew.movies.DataClasses.JsonProcesing;
import com.example.mathew.movies.DataClasses.Movies;
import com.example.mathew.movies.MainActivity;
import com.example.mathew.movies.RESTbackend.ConnectionResponse;
import com.example.mathew.movies.RESTbackend.RestConfig;

import org.json.JSONObject;

import java.util.ArrayList;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Karol on 11.05.2016.
 */
    public class SoketCommunicator extends AsyncTask<RestConfig, Void, ConnectionResponse> {

    //inicializacne premenne
    private Socket socket = null;
    private String SERVERNAME = "/data/FetakDusan25";


    public SoketCommunicator() {
    }



    //metoda na pripajanie, hlavna exekucna metoda
    protected ConnectionResponse doInBackground(RestConfig... params) {

        System.out.println("**********************SOCKET:     zacinam pripajanie*****************");

        try {

            //pripojenie na soket
            if(socket==null) {
                IO.Options opts = new IO.Options();
                opts.secure = false;
                opts.port = 1341;
                opts.reconnection = true;
                opts.forceNew = true;
                opts.timeout = 5000;

                socket = IO.socket("http://sandbox.touch4it.com:1341/?__sails_io_sdk_version=0.12.1", opts);
                socket.connect();

                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        System.out.println("**********************SOCKET:     PRIPOJENE NA SERVER*****************");
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        System.out.println("**********************SOCKET:     Odpojene*****************");
                    }
                });
            }

            //POST request
            if (params[0].getTYPE()==3) {

                //asynchronna odpoved
                Ack response = new Ack() {
                    @Override
                    public void call(Object... args) {
                        if (args[0] != null) {
                            try {
                                JSONObject odpoved = null;
                                odpoved = new JSONObject(args[0].toString());

                                if (odpoved.getInt("statusCode") == 200) {
                                    System.out.println("**********************SOCKET: POST USPESNY*****************");
                                } else {
                                    System.out.println("**********************SOCKET: POST ERROR CODE>" + odpoved.getInt("statusCode") + " *****************");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                //request
                JSONObject payload = new JSONObject();

                payload.put("url", SERVERNAME);
                JSONObject jasssones = new JSONObject();
                jasssones.put("data",params[0].getJsonObject());
                payload.put("data",jasssones);
                socket.emit("post", payload, response);
                System.out.println("**********************SOCKET: POST ODOSLANY*****************");
            }

            //GET request
            if (params[0].getTYPE()==1) {

                final JSONObject[] zoznamfilmov =  new JSONObject[1];


                //asynchronna odpoved
                Ack response = new Ack() {
                    @Override
                    public void call(Object... args) {
                        if (args[0] != null) {
                            try {
                                JSONObject odpoved = null;
                                odpoved = new JSONObject(args[0].toString());

                                if (odpoved.getInt("statusCode") == 200) {
                                    System.out.println("**********************SOCKET: GET USPESNY*****************");
                                    zoznamfilmov[0]=odpoved.getJSONObject("body");

                                } else {
                                    System.out.println("**********************SOCKET: GET ERROR CODE>" + odpoved.getInt("statusCode") + " *****************");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                //request
                JSONObject payload = new JSONObject();
                payload.put("url", SERVERNAME);

                socket.emit("get", payload, response);
                System.out.println("**********************SOCKET: GET ODOSLANY, USPAVAM VLAKNO*****************");
                Thread.sleep(3000);
                System.out.println("**********************SOCKET: VLAKNO SA ZOBUDILO***************** " + zoznamfilmov[0]);

                ArrayList<Movies> list = JsonProcesing.MoviesFromSocket(zoznamfilmov[0].getJSONArray("data"));

                return new ConnectionResponse(1, list);
            }

            //DELETE request
            if (params[0].getTYPE()==4) {

                //asynchronna odpoved
                Ack response = new Ack() {
                    @Override
                    public void call(Object... args) {
                        if (args[0] != null) {
                            try {
                                JSONObject odpoved = null;
                                odpoved = new JSONObject(args[0].toString());

                                if (odpoved.getInt("statusCode") == 200) {
                                    System.out.println("**********************SOCKET: DELETE USPESNY*****************");
                                } else {
                                    System.out.println("**********************SOCKET: DELETE ERROR CODE>" + odpoved.getInt("statusCode") + " *****************");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                //request
                JSONObject payload = new JSONObject();

                payload.put("url", SERVERNAME+"/"+params[0].getFilmID());
                socket.emit("delete", payload, response);
                System.out.println("**********************SOCKET: DELETE ODOSLANY*****************");
            }

            //PUT request
            if (params[0].getTYPE()==2) {

                //asynchronna odpoved
                Ack response = new Ack() {
                    @Override
                    public void call(Object... args) {
                        if (args[0] != null) {
                            try {
                                JSONObject odpoved = null;
                                odpoved = new JSONObject(args[0].toString());

                                if (odpoved.getInt("statusCode") == 200) {
                                    System.out.println("**********************SOCKET: PUT USPESNY*****************");
                                } else {
                                    System.out.println("**********************SOCKET: PUT ERROR CODE>" + odpoved.getInt("statusCode") + " *****************");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                //request
                JSONObject payload = new JSONObject();
                payload.put("url", SERVERNAME + "/" + params[0].getFilmID());
                JSONObject jasssones = new JSONObject();
                jasssones.put("data",params[0].getJsonObject());
                System.out.println("**********************SOCKET: " + jasssones.getString("data"));
                payload.put("data",jasssones);
                socket.emit("put", payload, response);
                System.out.println("**********************SOCKET: PUT ODOSLANY*****************");
            }



            }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    protected void onPostExecute(ConnectionResponse answer){
        super.onPostExecute(answer);
    }

    }
