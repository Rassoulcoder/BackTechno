package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.modele.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
