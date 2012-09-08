/**
 * Copyright (C) 2010  BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA  02110-1301, USA.
 **/
package org.ow2.bonita.util;

import java.util.Comparator;

import org.ow2.bonita.facade.runtime.impl.InternalProcessInstance;

public class ProcessInstanceLastUpdateComparator implements Comparator<InternalProcessInstance> {

	//sort instances from most recently modified to old modified instances such as:
	//list<instance> l
	//l(0).getUpdatedDate >= l(1).getUpdatedDate
  public int compare(InternalProcessInstance o1, InternalProcessInstance o2) {
    return o2.getLastUpdate().compareTo(o1.getLastUpdate());
  }
}