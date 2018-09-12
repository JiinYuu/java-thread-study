package com.lmq.study.thread.ch2;

import java.math.BigInteger;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe @RestController
public class VolatileCachedFactorizer {
	private volatile OneValueCache cache = new OneValueCache(null, null);
	
	@GetMapping("factor")
	public ResponseEntity<BigInteger[]> factor(BigInteger i) {
		BigInteger[] factors = this.cache.getFactors(i);
		if (Objects.isNull(factors)) {
			factors = this.fact(i);
			this.cache = new OneValueCache(i, factors);
		}
		return ResponseEntity.ok(factors);
	}

    private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
