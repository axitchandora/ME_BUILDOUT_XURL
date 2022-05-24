package com.crio.shorturl;

import java.util.HashMap;
import java.util.Random;

public class XUrlImpl implements XUrl{
    HashMap<String,Integer> shortUrlCount=new HashMap<>();
    HashMap<String,String> shortUrlToLongUrl=new HashMap<>();
    HashMap<String,String> longUrlToShortUrl=new HashMap<>();
    
    private String calculateHash(){
        String salt="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuilder hash=new StringBuilder();
        while(hash.length()<9){
            hash.append(salt.charAt(random.nextInt(salt.length())));
        }
        return hash.toString();
    }    

    @Override
    public String registerNewUrl(String longUrl) {
        if(longUrlToShortUrl.containsKey(longUrl))
            return "http://short.url/"+longUrlToShortUrl.get(longUrl);
        String shortUrl=calculateHash();
        shortUrlToLongUrl.put(shortUrl,longUrl);
        longUrlToShortUrl.put(longUrl, shortUrl);
        shortUrlCount.put(shortUrl,0);
        return "http://short.url/"+shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        String shortChoppedUrl=shortUrl.replace("http://short.url/", "").replaceAll("[^A-Za-z0-9]", "");
        if(shortUrlToLongUrl.containsKey(shortChoppedUrl)) return null;
        shortUrlToLongUrl.put(shortChoppedUrl,longUrl);
        longUrlToShortUrl.put(longUrl, shortChoppedUrl);
        shortUrlCount.put(shortChoppedUrl,0);
        return "http://short.url/"+shortChoppedUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        String shortChoppedUrl=shortUrl.replace("http://short.url/", "").replaceAll("[^A-Za-z0-9]", "");
        if(shortUrlToLongUrl.containsKey(shortChoppedUrl)){
            shortUrlCount.put(shortChoppedUrl,shortUrlCount.get(shortChoppedUrl)+1);
            return shortUrlToLongUrl.get(shortChoppedUrl);
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(longUrlToShortUrl.containsKey(longUrl)){
            return shortUrlCount.get(longUrlToShortUrl.get(longUrl));
        }
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        if(longUrlToShortUrl.containsKey(longUrl)){
            shortUrlToLongUrl.remove(longUrlToShortUrl.get(longUrl));
            shortUrlCount.remove(longUrlToShortUrl.get(longUrl));
            longUrlToShortUrl.remove(longUrl);
        }
        return null;
    }
    
}