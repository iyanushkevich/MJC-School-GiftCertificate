package com.epam.esm.dao.impl.util;

public final class SqlQuery {

    /*
    queries for tag table
    */
    public static final String TAG_FIND_ALL = "SELECT t FROM Tag t";
    public static final String TAG_FIND_BY_NAME = "SELECT t FROM Tag t WHERE t.name = :name";
    public static final String TAG_FIND_MOST_POPULAR_BY_USER_WHICH_HAS_HIGHEST_AMOUNT_ORDERS =
            "SELECT tag.id, tag.name FROM tag JOIN tag_has_gift_certificate ON tag.id = tag_has_gift_certificate.tag_id " +
                    "JOIN gift_certificate ON gift_certificate.id = tag_has_gift_certificate.gift_certificate_id " +
                    "WHERE gift_certificate.id IN (SELECT orders.gift_certificate_id FROM orders WHERE orders.user_id = " +
                    "(SELECT user.id FROM user WHERE user.id = (SELECT orders.user_id FROM orders GROUP BY orders.user_id " +
                    "ORDER BY SUM(orders.cost) DESC LIMIT 1))) GROUP BY tag.id ORDER BY COUNT(tag.id) DESC LIMIT 1";



    /*
    queries for user table
     */
    public static final String FIND_ALL_USERS = "SELECT u FROM User u";
    public static final String FIND_USER_BY_LOGIN = "SELECT u FROM User u WHERE u.login =:login";

    /*
   queries for orders table
    */
    public static final String FIND_ALL_ORDERS_BY_USER_ID = "SELECT o FROM Order o WHERE o.userId =:userId";

     /*
   queries for role table
    */
     public static final String FIND_ROLE_BY_NAME = "SELECT r FROM Role r WHERE r.name = :name";
}
