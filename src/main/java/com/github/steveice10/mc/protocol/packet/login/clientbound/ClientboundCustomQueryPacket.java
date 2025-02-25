package com.github.steveice10.mc.protocol.packet.login.clientbound;

import com.github.steveice10.mc.protocol.codec.MinecraftCodecHelper;
import com.github.steveice10.mc.protocol.codec.MinecraftPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.With;

import java.io.IOException;

@Data
@With
@AllArgsConstructor
public class ClientboundCustomQueryPacket implements MinecraftPacket {
    private final int messageId;
    private final @NonNull String channel;
    private final @NonNull byte[] data;

    public ClientboundCustomQueryPacket(ByteBuf in, MinecraftCodecHelper helper) throws IOException {
        this.messageId = helper.readVarInt(in);
        this.channel = helper.readString(in);
        this.data = helper.readByteArray(in, ByteBuf::readableBytes);
    }

    @Override
    public void serialize(ByteBuf out, MinecraftCodecHelper helper) throws IOException {
        helper.writeVarInt(out, this.messageId);
        helper.writeString(out, this.channel);
        out.writeBytes(this.data);
    }
}
