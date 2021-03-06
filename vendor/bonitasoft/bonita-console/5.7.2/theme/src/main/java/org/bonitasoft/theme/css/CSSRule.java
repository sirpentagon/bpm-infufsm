/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.theme.css;

import java.util.Map;

/**
 * @author Romain Bioteau
 *
 */
public interface CSSRule {

	/**
	 * @return the name of the rule
	 */
	String getName() ;
	
	/**
	 * @param name - the name of the rule
	 */
	void setName(String name) ;

	/**
	 * @return the comment of the rule
	 */
	String getComment() ;
	
	/**
	 * Set a comment for a rule
	 * @param comment
	 */
	void setComment(String comment) ;
	
	/**
	 * @return all the attributes of the rule
	 */
	Map<String, String> getAllProperties() ;


	/**
	 * @param key
	 * @param value
	 */
	void put(String key, String value) ;

	/**
	 * @param key
	 */
	void remove(String key) ;
	
	/**
	 * @param key
	 * @return
	 */
	String get(String key) ;
	
}
