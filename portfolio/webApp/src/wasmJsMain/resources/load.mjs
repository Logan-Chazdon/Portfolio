import { instantiate } from './portfolio.uninstantiated.mjs';

await wasmSetup;

instantiate({ skia: Module['asm'] });
