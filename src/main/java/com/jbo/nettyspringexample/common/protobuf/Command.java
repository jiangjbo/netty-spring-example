package com.jbo.nettyspringexample.common.protobuf;

import com.google.protobuf.*;

import java.util.Arrays;

public final class Command {

    private Command() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registerAllExtensions((ExtensionRegistryLite) registry);
    }

    public enum CommandType implements ProtocolMessageEnum {
        AUTH(1),
        PING(2),
        PONG(3),
        UPLOAD_DATA(4),
        PUSH_DATA(5),
        AUTH_BACK(11),
        UPLOAD_DATA_BACK(14),
        PUSH_DATA_BACK(15),;
        public static final int AUTH_VALUE = 1;
        public static final int PING_VALUE = 2;
        public static final int PONG_VALUE = 3;
        public static final int UPLOAD_DATA_VALUE = 4;
        public static final int PUSH_DATA_VALUE = 5;
        public static final int AUTH_BACK_VALUE = 11;
        public static final int UPLOAD_DATA_BACK_VALUE = 14;
        public static final int PUSH_DATA_BACK_VALUE = 15;

        public final int getNumber() {
            return value;
        }

        public static CommandType forNumber(int value) {
            return Arrays.stream(CommandType.values())
                    .filter(commandType -> commandType.value == value)
                    .findFirst()
                    .orElseGet(null);
        }

        public static Internal.EnumLiteMap<CommandType> internalGetValueMap() {
            return internalValueMap;
        }

        private static final Internal.EnumLiteMap<CommandType> internalValueMap = CommandType::forNumber;

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static Descriptors.EnumDescriptor getDescriptor() {
            return Command.getDescriptor().getEnumTypes().get(0);
        }

        private static final CommandType[] VALUES = values();

        public static CommandType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private final int value;

        CommandType(int value) {
            this.value = value;
        }

    }


    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static Descriptors.FileDescriptor descriptor;

    static {
        String[] descriptorData = {
                "\n\rCommand.proto\032\rCommand.proto*\204\001\n\013Comma" +
                        "ndType\022\010\n\004AUTH\020\001\022\010\n\004PING\020\002\022\010\n\004PONG\020\003\022\017\n\013" +
                        "UPLOAD_DATA\020\004\022\r\n\tPUSH_DATA\020\005\022\r\n\tAUTH_BAC" +
                        "K\020\013\022\024\n\020UPLOAD_DATA_BACK\020\016\022\022\n\016PUSH_DATA_B" +
                        "ACK\020\017B$\n\031com.jbo.nettyspringexample.common.protobufB\007Comm" +
                        "and"
        };
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(
                    Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData,
                        new Descriptors.FileDescriptor[]{Command.getDescriptor()}, assigner);
        Command.getDescriptor();
    }

}
