package com.crio.shorturl;

public interface XUrl {

  // If longUrl already has a corresponding shortUrl, return that shortUrl
  // If longUrl is new, create a new shortUrl for the longUrl and return it
  String registerNewUrl(String longUrl);

  // If shortUrl is already present, return null
  // Else, register the specified shortUrl for the given longUrl
  // Note: You don't need to validate if longUrl is already present, 
  //       assume it is always new i.e. it hasn't been seen before 
  String registerNewUrl(String longUrl, String shortUrl);

  // If shortUrl doesn't have a corresponding longUrl, return null
  // Else, return the corresponding longUrl
  String getUrl(String shortUrl);

  // Return the number of times the longUrl has been looked up using getUrl()
  Integer getHitCount(String longUrl);

  // Delete the mapping between this longUrl and its corresponding shortUrl
  // Do not zero the Hit Count for this longUrl
  String delete(String longUrl);

}

