module Projek {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;
    opens Marketplace;
    opens Marketplace.config;
    opens Marketplace.entities;
    opens Marketplace.repositories;
    opens Marketplace.services;
    opens Marketplace.views;
}