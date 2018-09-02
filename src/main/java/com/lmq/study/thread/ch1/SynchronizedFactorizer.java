package com.lmq.study.thread.ch1;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe @RestController
public class SynchronizedFactorizer {

	private @GuardedBy("this") BigInteger lastNumber;
	private @GuardedBy("this") BigInteger[] lastFactors;
	
	@GetMapping("factor")
	public synchronized ResponseEntity<BigInteger[]> factor(BigInteger i) {
		if(i.equals(this.lastNumber)) {
			return ResponseEntity.ok(this.lastFactors);
		}
		BigInteger[] factors = this.fact(i);
		this.lastNumber = i;
		this.lastFactors = factors;
		return ResponseEntity.ok(factors);
	}

	private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
