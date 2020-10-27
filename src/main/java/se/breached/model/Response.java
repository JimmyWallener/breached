package se.breached.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public interface Response {
    AtomicInteger numberOfBreaches(String password);
    ArrayList<?> breachDataFromEmail(String url);
}
