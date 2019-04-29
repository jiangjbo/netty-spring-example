package com.jbo.nettyspringexample.common.protobuf;

import com.google.protobuf.*;

import static com.jbo.nettyspringexample.common.protobuf.Command.CommandType;

public final class Message {

    private Message() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registerAllExtensions((ExtensionRegistryLite) registry);
    }

    public interface MessageBaseOrBuilder extends MessageOrBuilder {

        boolean hasClientId();

        String getClientId();

        ByteString getClientIdBytes();

        boolean hasCmd();

        CommandType getCmd();

        boolean hasData();

        String getData();

        ByteString getDataBytes();
    }

    public static final class MessageBase extends GeneratedMessageV3 implements MessageBaseOrBuilder {

        private MessageBase(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private MessageBase() {
            clientId_ = "";
            cmd_ = 1;
            data_ = "";
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MessageBase(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                        case 10: {
                            ByteString bs = input.readBytes();
                            bitField0_ |= 0x00000001;
                            clientId_ = bs;
                            break;
                        }
                        case 16: {
                            int rawValue = input.readEnum();
                            Command.CommandType value = Command.CommandType.forNumber(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(2, rawValue);
                            } else {
                                bitField0_ |= 0x00000002;
                                cmd_ = rawValue;
                            }
                            break;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            bitField0_ |= 0x00000004;
                            data_ = bs;
                            break;
                        }
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static Descriptors.Descriptor getDescriptor() {
            return Message.internal_static_MessageBase_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Message.internal_static_MessageBase_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(Message.MessageBase.class, Message.MessageBase.Builder.class);
        }

        private int bitField0_;
        private static final int CLIENT_ID_FIELD_NUMBER = 1;
        private volatile Object clientId_;

        public boolean hasClientId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        public String getClientId() {
            Object ref = clientId_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    clientId_ = s;
                }
                return s;
            }
        }

        public ByteString getClientIdBytes() {
            Object ref = clientId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                clientId_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        private static final int CMD_FIELD_NUMBER = 2;
        private int cmd_;

        public boolean hasCmd() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        public Command.CommandType getCmd() {
            Command.CommandType result = Command.CommandType.forNumber(cmd_);
            return result == null ? Command.CommandType.AUTH : result;
        }

        private static final int DATA_FIELD_NUMBER = 3;
        private volatile Object data_;

        public boolean hasData() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        public String getData() {
            Object ref = data_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    data_ = s;
                }
                return s;
            }
        }

        public ByteString getDataBytes() {
            Object ref = data_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                data_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            if (!hasClientId()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasCmd()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream output)
                throws java.io.IOException {
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                GeneratedMessageV3.writeString(output, 1, clientId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                output.writeEnum(2, cmd_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                GeneratedMessageV3.writeString(output, 3, data_);
            }
            unknownFields.writeTo(output);
        }

        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += GeneratedMessageV3.computeStringSize(1, clientId_);
            }
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
                size += CodedOutputStream
                        .computeEnumSize(2, cmd_);
            }
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
                size += GeneratedMessageV3.computeStringSize(3, data_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        private static final long serialVersionUID = 0L;

        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Message.MessageBase)) {
                return super.equals(obj);
            }
            Message.MessageBase other = (Message.MessageBase) obj;

            boolean result = true;
            result = result && (hasClientId() == other.hasClientId());
            if (hasClientId()) {
                result = result && getClientId()
                        .equals(other.getClientId());
            }
            result = result && (hasCmd() == other.hasCmd());
            if (hasCmd()) {
                result = result && cmd_ == other.cmd_;
            }
            result = result && (hasData() == other.hasData());
            if (hasData()) {
                result = result && getData()
                        .equals(other.getData());
            }
            result = result && unknownFields.equals(other.unknownFields);
            return result;
        }

        @Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptorForType().hashCode();
            if (hasClientId()) {
                hash = (37 * hash) + CLIENT_ID_FIELD_NUMBER;
                hash = (53 * hash) + getClientId().hashCode();
            }
            if (hasCmd()) {
                hash = (37 * hash) + CMD_FIELD_NUMBER;
                hash = (53 * hash) + cmd_;
            }
            if (hasData()) {
                hash = (37 * hash) + DATA_FIELD_NUMBER;
                hash = (53 * hash) + getData().hashCode();
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        @Deprecated
        public static final Parser<MessageBase> PARSER = new AbstractParser<MessageBase>() {
            public MessageBase parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new MessageBase(input, extensionRegistry);
            }
        };

        @Override
        public Parser<MessageBase> getParserForType() {
            return PARSER;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MessageBase prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageBaseOrBuilder {
            public static Descriptors.Descriptor getDescriptor() {
                return internal_static_MessageBase_descriptor;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_MessageBase_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(MessageBase.class, MessageBase.Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                }
            }

            public Builder clear() {
                super.clear();
                clientId_ = "";
                bitField0_ = (bitField0_ & ~0x00000001);
                cmd_ = 1;
                bitField0_ = (bitField0_ & ~0x00000002);
                data_ = "";
                bitField0_ = (bitField0_ & ~0x00000004);
                return this;
            }

            public Descriptors.Descriptor
            getDescriptorForType() {
                return Message.internal_static_MessageBase_descriptor;
            }

            public Message.MessageBase getDefaultInstanceForType() {
                return Message.MessageBase.getDefaultInstance();
            }

            public Message.MessageBase build() {
                Message.MessageBase result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Message.MessageBase buildPartial() {
                Message.MessageBase result = new Message.MessageBase(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.clientId_ = clientId_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.cmd_ = cmd_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.data_ = data_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder clone() {
                return super.clone();
            }

            public Builder setField(
                    Descriptors.FieldDescriptor field,
                    Object value) {
                return super.setField(field, value);
            }

            public Builder clearField(
                    Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            public Builder clearOneof(
                    Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            public Builder setRepeatedField(
                    Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return super.setRepeatedField(field, index, value);
            }

            public Builder addRepeatedField(
                    Descriptors.FieldDescriptor field,
                    Object value) {
                return super.addRepeatedField(field, value);
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof Message.MessageBase) {
                    return mergeFrom((Message.MessageBase) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(Message.MessageBase other) {
                if (other == Message.MessageBase.getDefaultInstance()) return this;
                if (other.hasClientId()) {
                    bitField0_ |= 0x00000001;
                    clientId_ = other.clientId_;
                    onChanged();
                }
                if (other.hasCmd()) {
                    setCmd(other.getCmd());
                }
                if (other.hasData()) {
                    bitField0_ |= 0x00000004;
                    data_ = other.data_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                if (!hasClientId()) {
                    return false;
                }
                if (!hasCmd()) {
                    return false;
                }
                return true;
            }

            private int bitField0_;

            private Object clientId_ = "";

            public boolean hasClientId() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            /**
             * <code>required string clientId = 1;</code>
             */
            public String getClientId() {
                Object ref = clientId_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        clientId_ = s;
                    }
                    return s;
                } else {
                    return (String) ref;
                }
            }

            /**
             * <code>required string clientId = 1;</code>
             */
            public ByteString getClientIdBytes() {
                Object ref = clientId_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    clientId_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>required string clientId = 1;</code>
             */
            public Builder setClientId(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000001;
                clientId_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required string clientId = 1;</code>
             */
            public Builder clearClientId() {
                bitField0_ = (bitField0_ & ~0x00000001);
                clientId_ = getDefaultInstance().getClientId();
                onChanged();
                return this;
            }

            /**
             * <code>required string clientId = 1;</code>
             */
            public Builder setClientIdBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000001;
                clientId_ = value;
                onChanged();
                return this;
            }

            private int cmd_ = 1;

            /**
             * <code>required .CommandType cmd = 2;</code>
             */
            public boolean hasCmd() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            /**
             * <code>required .CommandType cmd = 2;</code>
             */
            public Command.CommandType getCmd() {
                Command.CommandType result = Command.CommandType.forNumber(cmd_);
                return result == null ? Command.CommandType.AUTH : result;
            }

            /**
             * <code>required .CommandType cmd = 2;</code>
             */
            public Builder setCmd(Command.CommandType value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000002;
                cmd_ = value.getNumber();
                onChanged();
                return this;
            }

            /**
             * <code>required .CommandType cmd = 2;</code>
             */
            public Builder clearCmd() {
                bitField0_ = (bitField0_ & ~0x00000002);
                cmd_ = 1;
                onChanged();
                return this;
            }

            private Object data_ = "";

            /**
             * <code>optional string data = 3;</code>
             */
            public boolean hasData() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            /**
             * <code>optional string data = 3;</code>
             */
            public String getData() {
                Object ref = data_;
                if (!(ref instanceof String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        data_ = s;
                    }
                    return s;
                } else {
                    return (String) ref;
                }
            }

            /**
             * <code>optional string data = 3;</code>
             */
            public ByteString
            getDataBytes() {
                Object ref = data_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (String) ref);
                    data_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string data = 3;</code>
             */
            public Builder setData(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000004;
                data_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string data = 3;</code>
             */
            public Builder clearData() {
                bitField0_ = (bitField0_ & ~0x00000004);
                data_ = getDefaultInstance().getData();
                onChanged();
                return this;
            }

            /**
             * <code>optional string data = 3;</code>
             */
            public Builder setDataBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000004;
                data_ = value;
                onChanged();
                return this;
            }

            public final Builder setUnknownFields(
                    final UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            public final Builder mergeUnknownFields(
                    final UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }

        }

        private static final MessageBase DEFAULT_INSTANCE = new Message.MessageBase();

        public static MessageBase getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public MessageBase getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }


    private static final Descriptors.Descriptor internal_static_MessageBase_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_MessageBase_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {
                "\n\rMessage.proto\032\rCommand.proto\"H\n\013Messag" +
                        "eBase\022\020\n\010clientId\030\001 \002(\t\022\031\n\003cmd\030\002 \002(\0162\014.C" +
                        "ommandType\022\014\n\004data\030\003 \001(\tB$\n\031com.jbo.nettyspringexample.co" +
                        "mmon.protobufB\007Message"
        };
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public ExtensionRegistry assignDescriptors(
                            Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new Descriptors.FileDescriptor[]{
                                Command.getDescriptor(),
                        }, assigner);
        internal_static_MessageBase_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_MessageBase_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_MessageBase_descriptor, new String[]{"ClientId", "Cmd", "Data",});
        Command.getDescriptor();
    }

}
