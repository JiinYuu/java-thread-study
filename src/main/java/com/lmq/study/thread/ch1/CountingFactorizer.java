package com.lmq.study.thread.ch1;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe @RestController
public class CountingFactorizer {

	private final AtomicLong count = new AtomicLong(0);
	
	@GetMapping("factor")
	public ResponseEntity<BigInteger[]> factor(BigInteger i) {
		BigInteger[] factors = this.fact(i);
		this.count.incrementAndGet();
		return ResponseEntity.ok(factors);
	}

	public long getCount() {
		return this.count.get();
	}
	
    private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
