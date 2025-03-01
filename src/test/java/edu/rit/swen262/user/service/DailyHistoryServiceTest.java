package edu.rit.swen262.user.service;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

public class DailyHistoryServiceTest {
    @BeforeEach
    public void setUp() {
        DailyHistoryService dailyHistory = new DailyHistoryService("User1", new Date(100), 10, 2000); 
    }
}
