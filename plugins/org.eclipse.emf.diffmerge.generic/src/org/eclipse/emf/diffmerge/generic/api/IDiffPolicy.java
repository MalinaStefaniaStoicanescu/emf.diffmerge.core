/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.api;


/**
 * A policy that alters the construction of differences between model scopes
 * in a comparison.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IDiffPolicy<E, A, R> {
  
  /**
   * Return whether the given attribute values must be considered equal
   * @param value1_p the first non-null attribute value
   * @param value2_p the second non-null attribute value
   * @param attribute_p the non-null attribute concerned
   */
  boolean considerEqual(Object value1_p, Object value2_p, A attribute_p);
  
  /**
   * Return whether the given out-of-scope value must be considered equal to the
   * other given value. True may only be returned if the other value is also out of scope.
   * @param outOfScopeValue_p the non-null out-of-scope value
   * @param candidateValue_p the non-null other value
   * @param reference_p the non-null reference concerned
   */
  boolean considerEqualOutOfScope(E outOfScopeValue_p, E candidateValue_p,
      R reference_p);
  
  /**
   * Return whether the given attribute must be considered as ordered
   * @param attribute_p a non-null attribute within this scope
   */
  boolean considerOrderedAttribute(A attribute_p);
  
  /**
   * Return whether the given reference must be considered as ordered
   * @param reference_p a reference within this scope
   *        (null stands for root containment)
   */
  boolean considerOrderedReference(R reference_p);
  
  /**
   * Return whether the given attribute must be covered by the difference
   * detection algorithm
   * @param attribute_p a non-null attribute
   */
  boolean coverAttribute(A attribute_p);
  
  /**
   * Return whether the given match must be covered by the difference detection
   * algorithm
   * @param match_p a non-null match
   */
  boolean coverMatch(IMatch<E, A, R> match_p);
  
  /**
   * Return whether the given element, even though it is outside the TARGET and
   * REFERENCE scopes, must be taken into account when it is a value of the given reference.
   * If so, then corresponding differences can be detected and merging them means copying
   * references to the element as they are. If not, then those differences are ignored.
   * If the element belongs to either scope, then the value returned by this operation
   * has no impact.
   * Precondition: coverFeature(reference_p)
   * @param value_p a non-null element
   * @param reference_p a non-null reference
   */
  boolean coverOutOfScopeValue(E value_p, R reference_p);
  
  /**
   * Return whether the given reference must be covered by the difference
   * detection algorithm.
   * Precondition: 
   *  !reference_p.isContainment() && !reference_p.isContainer()
   * In other terms, this method is never called for containment or container
   * references. This is because those are implicitly determined by the elements
   * that are present in the compared model scopes.
   * @param reference_p a non-null reference
   */
  boolean coverReference(R reference_p);
  
  /**
   * Return whether the given value is significant for the given attribute.
   * If not, then its presence is ignored.
   * @param value_p a non-null attribute value
   * @param attribute_p a non-null attribute
   */
  boolean coverValue(Object value_p, A attribute_p);
  
}
