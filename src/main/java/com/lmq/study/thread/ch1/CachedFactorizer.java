package com.lmq.study.thread.ch1;

import java.math.BigInteger;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe @RestController
public class CachedFactorizer {

	private @GuardedBy("this") BigInteger lastNumber;
	private @GuardedBy("this") BigInteger[] lastFactors;
	private @GuardedBy("this") long hits;
	private @GuardedBy("this") long cacheHits;
	
	@GetMapping("factor")
	public ResponseEntity<BigInteger[]> factor(BigInteger i) {
		BigInteger[] factors = null;
		synchronized (this) {
			++hits;
			if (i.equals(this.lastNumber)) {
				factors = this.lastFactors.clone();
			}
		}
		if (Objects.isNull(factors)) {
			factors = this.fact(i);
			synchronized (this) {
				this.lastNumber = i;
				this.lastFactors = factors.clone();
			}
		}
		return ResponseEntity.ok(factors);
	}
	
	public synchronized long getHits() {
		return this.hits;
	}
	
	public synchronized double getCacheHitRatio() {
		return (double) this.cacheHits / (double) this.hits;
	}
	
	private BigInteger[] fact(BigInteger i) {
        return new BigInteger[] { i };
    }
}
