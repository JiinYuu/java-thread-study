package com.lmq.study.thread.ch1;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.NotThreadSafe;

@NotThreadSafe @RestController
public class UnsafeCachingFactorizer {

	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
	private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();
	
	@GetMapping("factor")
	public ResponseEntity<BigInteger[]> factor(BigInteger i) {
		if(i.equals(this.lastNumber.get())) {
			return ResponseEntity.ok(lastFactors.get());
		}
		BigInteger[] factors = this.fact(i);
		this.lastNumber.set(i);
		this.lastFactors.set(factors);
		return ResponseEntity.ok(factors);
	}
	
	private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
