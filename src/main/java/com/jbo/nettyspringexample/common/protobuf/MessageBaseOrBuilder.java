package com.jbo.nettyspringexample.common.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/**
 * @Author jiangbo
 * @Date 2019/4/29 14:26
 * @Version 1.0
 * @Description
 */
public interface MessageBaseOrBuilder extends MessageOrBuilder {

    boolean hasClientId();

    String getClientId();

    ByteString getClientIdBytes();

    boolean hasCmd();

    Command.CommandType getCmd();

    boolean hasData();

    String getData();

    ByteString getDataBytes();
}
