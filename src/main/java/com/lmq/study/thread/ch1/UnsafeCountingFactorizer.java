package com.lmq.study.thread.ch1;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.NotThreadSafe;

@NotThreadSafe @RestController
public class UnsafeCountingFactorizer {

	private long count = 0;
	
	@GetMapping("factor")
	public ResponseEntity<BigInteger[]> factor(BigInteger i) {
		BigInteger[] factors = this.fact(i);
		++count;
		return ResponseEntity.ok(factors);
	}
	
	public long getCount() {
		return this.count;
	}
	
	private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
