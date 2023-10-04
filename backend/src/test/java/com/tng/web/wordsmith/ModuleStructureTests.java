package com.tng.web.wordsmith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModuleStructureTests {

	@Test
	void verifyModules() {
		ApplicationModules modules = ApplicationModules.of(WordsmithApplication.class);
		modules.forEach(System.out::println);
		modules.verify();
	}
}
