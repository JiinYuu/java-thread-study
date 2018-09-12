package com.lmq.study.thread.ch2;

import java.util.HashSet;
import java.util.Set;

public class Secrets {

	public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
    }
}

class Secret {}
