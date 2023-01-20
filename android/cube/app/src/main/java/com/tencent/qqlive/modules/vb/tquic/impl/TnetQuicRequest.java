// Copyright (c) 2018 The Tencent Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.tencent.qqlive.modules.vb.tquic.impl;


public class TnetQuicRequest {
  public abstract static class Callback {
    public abstract void onConnect(int error_code) throws Exception;

    public abstract void onHeaderRecv(String header) throws Exception;

    public abstract void onDataRecv(byte[] body) throws Exception;

    public abstract void onComplete(int stream_error) throws Exception;

    public abstract void onClose(int error_code, String error_str) throws Exception;
  }

  private long mTnetQuicAdapter;
  private final Callback mCallback;

  public TnetQuicRequest(Callback callback, TnetConfig jConfig, int type) {
    mCallback = callback;
    try {
      mTnetQuicAdapter =
          nativeCreateTnetQuicAdapter(jConfig, type);
    } catch (RuntimeException e) {
      throw e;
    }
  }

  public void connect(String url, String ip_address) {
    nativeConnect(mTnetQuicAdapter, url, ip_address);
  }

  public void connectWithDomain(String host, int port) {
    nativeConnectWithDomain(mTnetQuicAdapter, host, port);
  }

  public void addHeaders(String key, String value) {
    nativeAddHeaders(mTnetQuicAdapter, key, value);
  }

  public boolean isConnectCompleted() {
    return nativeIsConnectCompleted(mTnetQuicAdapter);
  }

  public boolean sendRequest(byte[] body, int length, boolean fin) {
    return nativeSendRequest(mTnetQuicAdapter, body, length, fin);
  }

  public boolean isRequestFinished() {
    return nativeRequestFinished(mTnetQuicAdapter);
  }

  public void CancelRequest() {
    nativeCancelRequest(mTnetQuicAdapter);
  }

  public void Destroy() {
    nativeDestroy(mTnetQuicAdapter);
  }

  public void GetTnetStates(TnetStats jstats) {
    nativeGetTnetStates(mTnetQuicAdapter, jstats);
  }

  @CalledByNative
  private void onConnect(int error_code) {
    try {
      mCallback.onConnect(error_code);
    } catch (Exception e) {
      return;
    }
  }

  @CalledByNative
  private void onDataRecv(byte[] body) {
    try {
      mCallback.onDataRecv(body);
    } catch (Exception e) {
      return;
    }
  }

  @CalledByNative
  private void onComplete(int stream_error) {
    try {
      mCallback.onComplete(stream_error);
    } catch (Exception e) {
      return;
    }
  }

  @CalledByNative
  private void onClose(int error_code, String error_str) {
    try {
      mCallback.onClose(error_code, error_str);
    } catch (Exception e) {
      return;
    }
  }

  private native long nativeCreateTnetQuicAdapter(TnetConfig jConfig, int type);

  private native void nativeConnect(long nativePtr, String url, String ip);

  private native void nativeConnectWithDomain(long nativePtr, String host, int port);

  private native void nativeAddHeaders(long nativePtr, String key, String value);

  private native boolean nativeIsConnectCompleted(long nativePtr);

  private native boolean nativeSendRequest(long nativePtr, byte[] body, int length, boolean fin);

  private native boolean nativeRequestFinished(long nativePtr);

  private native void nativeCancelRequest(long nativePtr);

  private native void nativeDestroy(long nativePtr);

  private native void nativeGetTnetStates(long nativePtr, TnetStats jstats);

}

