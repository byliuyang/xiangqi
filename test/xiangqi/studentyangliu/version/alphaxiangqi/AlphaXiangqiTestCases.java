/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

/**
 * When you have changed the name of this package to the reflect your initials, and added
 * your first file to this package, you can remove this file.
 * @version Dec 26, 2016
 */
package xiangqi.studentyangliu.version.alphaxiangqi;

import org.junit.jupiter.api.Test;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.XiangqiGameVersion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test cases for Alpha Xiangqi
 * @version Jan 28, 2016
 */
public class AlphaXiangqiTestCases {
    @Test
    public void factoryProducesAlphaXiangqiGame() {
        assertNotNull(XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.ALPHA_XQ));
    }
}