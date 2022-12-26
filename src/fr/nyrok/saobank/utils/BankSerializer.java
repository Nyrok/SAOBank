package fr.nyrok.saobank.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.nyrok.saobank.objects.Bank;

final public class BankSerializer {
    private final Gson gson;

    public BankSerializer(){
        this.gson = createGsonInstance();
    }

    private Gson createGsonInstance(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

    public String serialize(Bank bank){
        return this.gson.toJson(bank);
    }

    public Bank deserialize(String json){
        return this.gson.fromJson(json, Bank.class);
    }
}
