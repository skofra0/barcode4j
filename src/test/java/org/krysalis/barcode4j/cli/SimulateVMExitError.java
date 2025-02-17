/*
 * Copyright 2002-2004 Jeremias Maerki.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.krysalis.barcode4j.cli;

/**
 * Special Error class to simulate VM shutdown without really doing so.
 * 
 * @author Jeremias Maerki
 * @version $Id: SimulateVMExitError.java,v 1.2 2004-09-04 20:26:14 jmaerki Exp $
 */
public class SimulateVMExitError extends Error {

  private static final long serialVersionUID = 1L;

  public SimulateVMExitError() {
    super();
  }

}
