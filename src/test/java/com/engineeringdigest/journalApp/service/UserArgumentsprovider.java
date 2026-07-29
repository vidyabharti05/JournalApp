package com.engineeringdigest.journalApp.service;

import com.engineeringdigest.journalApp.entity.User;
import lombok.Builder;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import lombok.Builder;

import java.util.stream.Stream;

@Disabled
public class UserArgumentsprovider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("shyam").password("shyam").build()),
                Arguments.of(User.builder().username("sunita").password("sunita").build())
        );
    }
}
