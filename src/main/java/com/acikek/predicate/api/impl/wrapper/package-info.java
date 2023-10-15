/**
 * This is an internal package containing type wrappers over {@link com.acikek.predicate.api.RegularPredicate},
 * specifically for implementations on vanilla predicate classes.
 * <p>
 * This is necessary due to two important limitations with Fabric Loom:
 * <ol>
 *     <li>Injected interfaces do not retain their generic types</li>
 *     <li>Loom cannot inject interfaces that are inner members of a class</li>
 * </ol>
 * Thus, not only does each vanilla predicate require its own concrete type, but each type needs its own
 * class file.
 */
package com.acikek.predicate.api.impl.wrapper;