/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.ow2.bonita.facade.rest.stringconverter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.StringConverter;
import org.ow2.bonita.util.xml.XStreamUtil;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author Elias Ricken de Medeiros
 * 
 */
@Provider
public class HashMapStringConverter implements StringConverter<HashMap<?, ?>> {
  private static Logger LOG = Logger.getLogger(HashMapStringConverter.class.getName());

  @Override
  public HashMap<?, ?> fromString(String str) {
    try {
      if (!str.startsWith("<")) {
        str = URLDecoder.decode(str, "UTF-8");
      }
    } catch (final UnsupportedEncodingException e) {
      if (LOG.isLoggable(Level.WARNING)) {
        LOG.warning("Cannot decode " + str + " using UTF-8");
      }
    }
    final XStream xstream = XStreamUtil.getDefaultXstream();
    return (HashMap<?, ?>) xstream.fromXML(str);
  }

  @Override
  public String toString(final HashMap<?, ?> value) {
    final XStream xstream = XStreamUtil.getDefaultXstream();
    return xstream.toXML(value);
  }

}
