/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  The ASF licenses this file to You
* under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.  For additional information regarding
* copyright in this work, please see the NOTICE file in the top level
* directory of this distribution.
*/
package org.apache.abdera.parser.stax;

import javax.xml.namespace.QName;

import org.apache.abdera.model.IRIElement;
import org.apache.abdera.util.URIHelper;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.i18n.iri.IRISyntaxException;
import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMXMLParserWrapper;

public class FOMIRI 
  extends FOMElement 
  implements IRIElement {

  private static final long serialVersionUID = -8434722753544181200L;

  public FOMIRI(QName qname) {
    super(qname);
  }
  
  public FOMIRI(QName qname, String value) throws IRISyntaxException {
    this(qname);
    setValue(value);
  }
  
  protected FOMIRI(
    String name,
    OMNamespace namespace,
    OMContainer parent,
    OMFactory factory)
      throws OMException {
    super(name, namespace, parent, factory);
  }
  
  protected FOMIRI(
    QName qname, 
    OMContainer parent, 
    OMFactory factory) 
      throws OMException {
    super(qname, parent, factory);
  }

  protected FOMIRI(
    QName qname, 
    OMContainer parent, 
    OMFactory factory,
    OMXMLParserWrapper builder) 
      throws OMException {
    super(qname, parent, factory, builder);
  }
  
  public IRI getValue() throws IRISyntaxException {
    return _getUriValue(getText());
  }

  public void setValue(String iri) throws IRISyntaxException {
    if (iri != null)
      setText((new IRI(iri)).toString());
    else
      _removeAllChildren();

  }
  
  public IRI getResolvedValue() throws IRISyntaxException {
    return _resolve(getResolvedBaseUri(), getValue());
  }

  public void setNormalizedValue(String uri) throws IRISyntaxException {
    if (uri != null)
      setValue(URIHelper.normalize(uri));
    else 
      setValue(null);
  }
}
