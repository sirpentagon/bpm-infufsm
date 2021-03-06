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
package org.ow2.bonita.facade.impl;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ow2.bonita.facade.APIAccessor;
import org.ow2.bonita.facade.ManagementAPI;
import org.ow2.bonita.facade.def.element.BusinessArchive;
import org.ow2.bonita.facade.def.majorElement.ProcessDefinition;
import org.ow2.bonita.facade.exception.DeploymentException;
import org.ow2.bonita.facade.exception.ProcessNotFoundException;
import org.ow2.bonita.facade.exception.RuleAlreadyExistsException;
import org.ow2.bonita.facade.exception.RuleNotFoundException;
import org.ow2.bonita.facade.exception.UndeletableInstanceException;
import org.ow2.bonita.facade.exception.UndeletableProcessException;
import org.ow2.bonita.facade.exception.UserNotFoundException;
import org.ow2.bonita.facade.internal.AbstractRemoteManagementAPI;
import org.ow2.bonita.facade.privilege.PrivilegePolicy;
import org.ow2.bonita.facade.privilege.Rule;
import org.ow2.bonita.facade.privilege.Rule.RuleType;
import org.ow2.bonita.facade.uuid.AbstractUUID;
import org.ow2.bonita.facade.uuid.ProcessDefinitionUUID;
import org.ow2.bonita.identity.auth.DomainOwner;
import org.ow2.bonita.identity.auth.PasswordOwner;
import org.ow2.bonita.identity.auth.RESTUserOwner;
import org.ow2.bonita.identity.auth.UserOwner;

/**
 * 
 * @author Elias Ricken de Medeiros
 *
 */
public class AbstractRemoteManagementAPIImpl implements AbstractRemoteManagementAPI {

  private static final long serialVersionUID = 7816343722790053864L;

  protected Map<String, ManagementAPI> apis = new HashMap<String, ManagementAPI>();

  protected ManagementAPI getAPI(final Map<String, String> options) {
  	final String queryList = options.get(APIAccessor.QUERYLIST_OPTION);
  	final String user = options.get(APIAccessor.USER_OPTION);
  	final String domain = options.get(APIAccessor.DOMAIN_OPTION);
  	final String restUser = options.get(APIAccessor.REST_USER_OPTION);
  	UserOwner.setUser(user);
  	DomainOwner.setDomain(domain);
  	if (restUser != null) {
  	  RESTUserOwner.setUser(restUser);
  	  String restPswd = options.get(APIAccessor.PASSWORD_HASH_OPTION);
      PasswordOwner.setPassword(restPswd);
  	}
  	
    if (!apis.containsKey(queryList)) {
      apis.put(queryList, new StandardAPIAccessorImpl().getManagementAPI(queryList));
    }
    return apis.get(queryList);
  }

  public ProcessDefinition deploy(final BusinessArchive businessArchive, final Map<String, String> options)
      throws DeploymentException {
    return getAPI(options).deploy(businessArchive);
  }

  public void deployJar(final String jarName, final byte[] jar, final Map<String, String> options)
      throws DeploymentException {
    getAPI(options).deployJar(jarName, jar);
  }

  public Set<String> getAvailableJars(final Map<String, String> options) throws RemoteException {
    return getAPI(options).getAvailableJars();
  }

  public void removeJar(final String jarName, final Map<String, String> options) throws DeploymentException {
    getAPI(options).removeJar(jarName);
  }

  public void deleteProcess(final ProcessDefinitionUUID processUUID, final Map<String, String> options)
      throws ProcessNotFoundException, UndeletableProcessException,
      UndeletableInstanceException {
    getAPI(options).deleteProcess(processUUID);
  }

  public void deleteAllProcesses(final Map<String, String> options) throws UndeletableInstanceException,
      RemoteException, UndeletableProcessException {
    getAPI(options).deleteAllProcesses();
  }

  public String getLoggedUser(final Map<String, String> options) throws RemoteException {
    return getAPI(options).getLoggedUser();
  }

  public void addMetaData(final String key, final String value, final Map<String, String> options) throws RemoteException {
    getAPI(options).addMetaData(key, value);
  }

  public void deleteMetaData(final String key, final Map<String, String> options) throws RemoteException {
    getAPI(options).deleteMetaData(key);
  }

  public String getMetaData(final String key, final Map<String, String> options) throws RemoteException {
    return getAPI(options).getMetaData(key);
  }

  public void archive(ProcessDefinitionUUID processUUID, final Map<String, String> options)
      throws DeploymentException {
    getAPI(options).archive(processUUID);
  }

  public void disable(ProcessDefinitionUUID processUUID, final Map<String, String> options)
      throws DeploymentException {
    getAPI(options).disable(processUUID);
  }

  public void enable(ProcessDefinitionUUID processUUID, final Map<String, String> options)
      throws DeploymentException {
    getAPI(options).enable(processUUID);
  }

  public Boolean isUserAdmin(final String username, final Map<String, String> options) throws UserNotFoundException,
      RemoteException {
    return getAPI(options).isUserAdmin(username);
  }

  public Boolean checkUserCredentials(final String username, final String password, final Map<String, String> options)
      throws RemoteException {
    return getAPI(options).checkUserCredentials(username, password);
  }
  
	public Boolean checkUserCredentialsWithPasswordHash(final String username,
			final String passwordHash, final Map<String, String> options)
			throws RemoteException {
		return getAPI(options).checkUserCredentialsWithPasswordHash(username, passwordHash);
	}  

  public Rule createRule(final String name, final String label, final String description,
      final RuleType type, final Map<String, String> options) throws RemoteException, RuleAlreadyExistsException {
    return getAPI(options).createRule(name, label, description, type);
  }
  
  public List<Rule> getRules(final RuleType ruleType, final int fromIndex, final int pageSige, final Map<String, String> options) throws RemoteException {
    return getAPI(options).getRules(ruleType, fromIndex, pageSige);
  }

  public long getNumberOfRules(final RuleType ruleType, final Map<String, String> options) throws RemoteException {
    return getAPI(options).getNumberOfRules(ruleType);
  }

  public PrivilegePolicy getRuleTypePolicy(final RuleType ruleType, final Map<String, String> options) throws RemoteException {
    return getAPI(options).getRuleTypePolicy(ruleType);
  }

  public void setRuleTypePolicy(final RuleType ruleType, final PrivilegePolicy newPolicy, final Map<String, String> options) throws RemoteException {
    getAPI(options).setRuleTypePolicy(ruleType, newPolicy);
  }

  public <E extends AbstractUUID> void addExceptionsToRuleByUUID(final String ruleUUID, final Set<E> exceptions,
      final Map<String, String> options) throws RuleNotFoundException, RemoteException {
    getAPI(options).addExceptionsToRuleByUUID(ruleUUID, exceptions);
  }

  public void deleteRuleByUUID(final String ruleUUID, final Map<String, String> options) throws RuleNotFoundException,
      RemoteException {
    getAPI(options).deleteRuleByUUID(ruleUUID);
  }

  public List<Rule> getAllRules(final Map<String, String> options) throws RemoteException {
    return getAPI(options).getAllRules();
  }

  public Rule getRuleByUUID(final String ruleUUID, final Map<String, String> options) throws RuleNotFoundException, RemoteException {
    return getAPI(options).getRuleByUUID(ruleUUID);
  }

  public <E extends AbstractUUID> void removeExceptionsFromRuleByUUID(final String ruleUUID, final Set<E> exceptions,
      final Map<String, String> options) throws RuleNotFoundException, RemoteException {
    getAPI(options).removeExceptionsFromRuleByUUID(ruleUUID, exceptions);
  }

  public Rule updateRuleByUUID(final String ruleUUID, final String name, final String label, final String description,
      final Map<String, String> options) throws RuleNotFoundException, RuleAlreadyExistsException, RemoteException {
    return getAPI(options).updateRuleByUUID(ruleUUID, name, label, description);
  }

  public void updateMigrationDate(final ProcessDefinitionUUID processUUID, 
      final Date migrationDate, Map<String, String> options)
      throws ProcessNotFoundException, RemoteException {
    getAPI(options).updateMigrationDate(processUUID, migrationDate);
  }

  @Override
  public void setResource(final ProcessDefinitionUUID processUUID,
      final String resourcePath, final byte[] content, final Map<String, String> options)
      throws ProcessNotFoundException {
    getAPI(options).setResource(processUUID, resourcePath, content);
  }

}
