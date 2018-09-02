package com.lmq.study.thread.ch1;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe @RestController
public class StatelessFactorizer {
	
	@GetMapping("factor")
	public ResponseEntity<BigInteger[]> factor(BigInteger i) {
		BigInteger[] factors = this.fact(i);
		return ResponseEntity.ok(factors);
	}

    private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
