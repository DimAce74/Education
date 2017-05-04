package auth;

import securesocial.core.BasicProfile;
import securesocial.core.PasswordInfo;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;
import securesocial.core.services.SaveMode;

import java.util.concurrent.CompletionStage;

/**
 * Created by db2admin on 04.05.2017.
 */
public class AuthService extends BaseUserService {
    
    @Override
    public CompletionStage doSave(BasicProfile user, SaveMode mode) {
        return null;
    }

    @Override
    public CompletionStage<Token> doSaveToken(Token token) {
        return null;
    }

    @Override
    public CompletionStage doLink(Object current, BasicProfile to) {
        return null;
    }

    @Override
    public CompletionStage<BasicProfile> doFind(String providerId, String userId) {
        return null;
    }

    @Override
    public CompletionStage<PasswordInfo> doPasswordInfoFor(Object user) {
        return null;
    }

    @Override
    public CompletionStage<BasicProfile> doUpdatePasswordInfo(Object user, PasswordInfo info) {
        return null;
    }

    @Override
    public CompletionStage<Token> doFindToken(String tokenId) {
        return null;
    }

    @Override
    public CompletionStage<BasicProfile> doFindByEmailAndProvider(String email, String providerId) {
        return null;
    }

    @Override
    public CompletionStage<Token> doDeleteToken(String uuid) {
        return null;
    }

    @Override
    public void doDeleteExpiredTokens() {

    }
}
