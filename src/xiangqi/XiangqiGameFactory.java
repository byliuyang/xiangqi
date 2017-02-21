/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi;

import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.studentyliu17.version.alpha.AlphaXiangqiGame;
import xiangqi.studentyliu17.version.beta.BetaPiecesInitializer;
import xiangqi.studentyliu17.version.beta.BetaRuleSet;
import xiangqi.studentyliu17.version.beta.BetaValidatorSet;
import xiangqi.studentyliu17.version.common.BoardState;
import xiangqi.studentyliu17.version.gamma.GammaPiecesInitializer;
import xiangqi.studentyliu17.version.gamma.GammaRuleSet;
import xiangqi.studentyliu17.version.gamma.GammaValidatorSet;
import xiangqi.studentyliu17.version.common.PiecesInitializer;
import xiangqi.studentyliu17.version.common.RuleSet;
import xiangqi.studentyliu17.version.common.ValidatorSet;
import xiangqi.studentyliu17.version.common.XiangqiGameImpl;
import xiangqi.studentyliu17.version.common.XiangqiGameState;

/**
 * A simple factory object that creates the appropriate instance of a XiangqiGame.
 *
 * @version Dec 26, 2016
 */
public class XiangqiGameFactory {
    private static final int BETA_BOARD_WIDTH  = 5;
    private static final int BETA_BOARD_HEIGHT = 5;
    
    private static final int GAMMA_BOARD_WIDTH  = 9;
    private static final int GAMMA_BOARD_HEIGHT = 10;
    
    /**
     * Factory method that returns an instance of the requested game.
     *
     * @param version the version requested
     *
     * @return the instance of the requested game
     */
    public static XiangqiGame makeXiangqiGame(XiangqiGameVersion version) {
    
        XiangqiGame game;
        PiecesInitializer initializer;
        BoardState boardState;
        ValidatorSet validatorSet;
        RuleSet ruleSet;
        switch (version) {
            case ALPHA_XQ:
                return new AlphaXiangqiGame();
            case BETA_XQ:
                initializer = new BetaPiecesInitializer();
                boardState = BoardState.makeBoardState(BETA_BOARD_WIDTH, BETA_BOARD_HEIGHT,
                                                       initializer);
                validatorSet = new BetaValidatorSet();
                ruleSet = new BetaRuleSet();
                break;
            case GAMMA_XQ:
                initializer = new GammaPiecesInitializer();
                boardState = BoardState.makeBoardState(GAMMA_BOARD_WIDTH, GAMMA_BOARD_HEIGHT,
                                                       initializer);
                validatorSet = new GammaValidatorSet();
                ruleSet = new GammaRuleSet();
                break;
            default:
                System.out.println("Not implemented yet!");
                return null;
        }
        XiangqiGameState gameState = XiangqiGameState.makeGameState(boardState);
        validatorSet.initialize();
        game = XiangqiGameImpl.makeXiangqiGame(ruleSet, validatorSet, gameState);
        return game;
    }
}
