package org.ow2.bonita.connector.examples.test;

import org.ow2.bonita.connector.core.Connector;
import org.ow2.bonita.connector.core.ConnectorTest;
import org.ow2.bonita.connector.examples.PrimitiveDataTypesArrayConnector;

public class PrimitiveDataTypesArrayConnectorTest extends ConnectorTest {

  @Override
  protected Class<? extends Connector> getConnectorClass() {
    return PrimitiveDataTypesArrayConnector.class;
  }
}
