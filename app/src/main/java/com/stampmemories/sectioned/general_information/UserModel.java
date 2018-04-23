package com.stampmemories.sectioned.general_information;

import android.text.TextUtils;

/**
 * Created by AppsterBiz on 11-Apr-18
 */

public class UserModel {
    private String phone;

    private String location;

    private String provider_id;

    private String locale;

    private String country_locale;

    private String lastname;

    private String gift_balance;

    private String credit_card_token;

    private String id;

    private String representative_last_name;

    private String accept_datetime;

    private String api_token;

    private String earned;

    private String longitude;

    private String social_type;

    private String credit_balance;

    private String mobile_verified;

    private String profile_url;

    private String account_id;

    private String representative_first_name;

    private String user_type;

    private String status;

    private String is_provider;

    private String profile_image;

    private String potential;

    private String provider;

    private String firstname;

    private String devicetoken;

    private String social_id;

    private String country_code;

    private String ip;

    private String business_name;

    private String document_verfied;

    private String bio;

    private String email;

    private String slug;

    private String devicetype;

    private String latitude;

    private String terms_and_condition;

    private String customer_id;

    public String getPhone() {
        return getValidString(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return getValidString(location);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvider_id() {
        return getValidString(provider_id);
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getLocale() {
        return getValidString(locale);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCountry_locale() {
        return getValidString(country_locale);
    }

    public void setCountry_locale(String country_locale) {
        this.country_locale = country_locale;
    }

    public String getLastname() {
        return getValidString(lastname);
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGift_balance() {
        return getValidString(gift_balance);
    }

    public void setGift_balance(String gift_balance) {
        this.gift_balance = gift_balance;
    }

    public String getCredit_card_token() {
        return getValidString(credit_card_token);
    }

    public void setCredit_card_token(String credit_card_token) {
        this.credit_card_token = credit_card_token;
    }

    public String getId() {
        return getValidString(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepresentative_last_name() {
        return getValidString(representative_last_name);
    }

    public void setRepresentative_last_name(String representative_last_name) {
        this.representative_last_name = representative_last_name;
    }

    public String getAccept_datetime() {
        return getValidString(accept_datetime);
    }

    public void setAccept_datetime(String accept_datetime) {
        this.accept_datetime = accept_datetime;
    }

    public String getApi_token() {
        return getValidString(api_token);
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getEarned() {
        return getValidString(earned);
    }

    public void setEarned(String earned) {
        this.earned = earned;
    }

    public String getLongitude() {
        return getValidString(longitude);
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSocial_type() {
        return getValidString(social_type);
    }

    public void setSocial_type(String social_type) {
        this.social_type = social_type;
    }

    public String getCredit_balance() {
        return getValidString(credit_balance);
    }

    public void setCredit_balance(String credit_balance) {
        this.credit_balance = credit_balance;
    }

    public String getMobile_verified() {
        return getValidString(mobile_verified);
    }

    public void setMobile_verified(String mobile_verified) {
        this.mobile_verified = mobile_verified;
    }

    public String getProfile_url() {
        return getValidString(profile_url);
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getAccount_id() {
        return getValidString(account_id);
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRepresentative_first_name() {
        return getValidString(representative_first_name);
    }

    public void setRepresentative_first_name(String representative_first_name) {
        this.representative_first_name = representative_first_name;
    }

    public String getUser_type() {
        return getValidString(user_type);
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getStatus() {
        return getValidString(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_provider() {
        return getValidString(is_provider);
    }

    public void setIs_provider(String is_provider) {
        this.is_provider = is_provider;
    }

    public String getProfile_image() {
        return getValidString(profile_image);
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPotential() {
        return getValidString(potential);
    }

    public void setPotential(String potential) {
        this.potential = potential;
    }

    public String getProvider() {
        return getValidString(provider);
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFirstname() {
        return getValidString(firstname);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDevicetoken() {
        return getValidString(devicetoken);
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getSocial_id() {
        return getValidString(social_id);
    }

    public void setSocial_id(String social_id) {
        this.social_id = social_id;
    }

    public String getCountry_code() {
        return getValidString(country_code);
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getIp() {
        return getValidString(ip);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBusiness_name() {
        return getValidString(business_name);
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getDocument_verfied() {
        return getValidString(document_verfied);
    }

    public void setDocument_verfied(String document_verfied) {
        this.document_verfied = document_verfied;
    }

    public String getBio() {
        return getValidString(bio);
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return getValidString(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlug() {
        return getValidString(slug);
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDevicetype() {
        return getValidString(devicetype);
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getLatitude() {
        return getValidString(latitude);
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTerms_and_condition() {
        return getValidString(terms_and_condition);
    }

    public void setTerms_and_condition(String terms_and_condition) {
        this.terms_and_condition = terms_and_condition;
    }

    public String getCustomer_id() {
        return getValidString(customer_id);
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public static String getValidString(String input) {
        if (!TextUtils.isEmpty(input))
            return (input);
        else return "";
    }

}
