/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.ow2.bonita.util;

import java.util.Collection;

import org.ow2.bonita.env.Environment;
import org.ow2.bonita.facade.uuid.ProcessDefinitionUUID;
import org.ow2.bonita.services.DocumentationManager;

public class ServerWebDeleteDocumentsOfProcessesCommand implements Command<Void> {

    private static final long serialVersionUID = -776790363550035416L;

    private final Collection<ProcessDefinitionUUID> processUUIDs;

    public ServerWebDeleteDocumentsOfProcessesCommand(final Collection<ProcessDefinitionUUID> processUUIDs) {
        super();
        this.processUUIDs = processUUIDs;
    }

    @Override
    public Void execute(final Environment environment) throws Exception {
        final DocumentationManager documentationManager = EnvTool.getDocumentationManager();
        for (final ProcessDefinitionUUID processUUID : processUUIDs) {
            documentationManager.deleteFolderOfProcessDefinition(processUUID);
        }
        return null;
    }
}
