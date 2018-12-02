//
// Autogenerated by Thrift Compiler (0.8.0)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//


//HELPER FUNCTIONS AND STRUCTURES

HelloServer_helloString_args = function(args) {
  this.msg = null;
  if (args) {
    if (args.msg !== undefined) {
      this.msg = args.msg;
    }
  }
};
HelloServer_helloString_args.prototype = {};
HelloServer_helloString_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.msg = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloString_args.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloString_args');
  if (this.msg) {
    output.writeFieldBegin('msg', Thrift.Type.STRING, 1);
    output.writeString(this.msg);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloString_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
HelloServer_helloString_result.prototype = {};
HelloServer_helloString_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.STRING) {
        this.success = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloString_result.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloString_result');
  if (this.success) {
    output.writeFieldBegin('success', Thrift.Type.STRING, 0);
    output.writeString(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloInt_args = function(args) {
  this.num = null;
  if (args) {
    if (args.num !== undefined) {
      this.num = args.num;
    }
  }
};
HelloServer_helloInt_args.prototype = {};
HelloServer_helloInt_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.num = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloInt_args.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloInt_args');
  if (this.num) {
    output.writeFieldBegin('num', Thrift.Type.I32, 1);
    output.writeI32(this.num);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloInt_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
HelloServer_helloInt_result.prototype = {};
HelloServer_helloInt_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.I32) {
        this.success = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloInt_result.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloInt_result');
  if (this.success) {
    output.writeFieldBegin('success', Thrift.Type.I32, 0);
    output.writeI32(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloBoolean_args = function(args) {
  this.flag = null;
  if (args) {
    if (args.flag !== undefined) {
      this.flag = args.flag;
    }
  }
};
HelloServer_helloBoolean_args.prototype = {};
HelloServer_helloBoolean_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.BOOL) {
        this.flag = input.readBool().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloBoolean_args.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloBoolean_args');
  if (this.flag) {
    output.writeFieldBegin('flag', Thrift.Type.BOOL, 1);
    output.writeBool(this.flag);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloBoolean_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
HelloServer_helloBoolean_result.prototype = {};
HelloServer_helloBoolean_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.BOOL) {
        this.success = input.readBool().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloBoolean_result.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloBoolean_result');
  if (this.success) {
    output.writeFieldBegin('success', Thrift.Type.BOOL, 0);
    output.writeBool(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloVoid_args = function(args) {
};
HelloServer_helloVoid_args.prototype = {};
HelloServer_helloVoid_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloVoid_args.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloVoid_args');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloVoid_result = function(args) {
};
HelloServer_helloVoid_result.prototype = {};
HelloServer_helloVoid_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloVoid_result.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloVoid_result');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloNull_args = function(args) {
};
HelloServer_helloNull_args.prototype = {};
HelloServer_helloNull_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloNull_args.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloNull_args');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_helloNull_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
HelloServer_helloNull_result.prototype = {};
HelloServer_helloNull_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.STRING) {
        this.success = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_helloNull_result.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_helloNull_result');
  if (this.success) {
    output.writeFieldBegin('success', Thrift.Type.STRING, 0);
    output.writeString(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_request_args = function(args) {
  this.request = null;
  if (args) {
    if (args.request !== undefined) {
      this.request = args.request;
    }
  }
};
HelloServer_request_args.prototype = {};
HelloServer_request_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRUCT) {
        this.request = new FirstRequest();
        this.request.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_request_args.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_request_args');
  if (this.request) {
    output.writeFieldBegin('request', Thrift.Type.STRUCT, 1);
    this.request.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServer_request_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
HelloServer_request_result.prototype = {};
HelloServer_request_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.STRUCT) {
        this.success = new FirstResponse();
        this.success.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

HelloServer_request_result.prototype.write = function(output) {
  output.writeStructBegin('HelloServer_request_result');
  if (this.success) {
    output.writeFieldBegin('success', Thrift.Type.STRUCT, 0);
    this.success.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

HelloServerClient = function(input, output) {
    this.input = input;
    this.output = (!output) ? input : output;
    this.seqid = 0;
};
HelloServerClient.prototype = {};
HelloServerClient.prototype.helloString = function(msg) {
  this.send_helloString(msg);
  return this.recv_helloString();
};

HelloServerClient.prototype.send_helloString = function(msg) {
  this.output.writeMessageBegin('helloString', Thrift.MessageType.CALL, this.seqid);
  var args = new HelloServer_helloString_args();
  args.msg = msg;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

HelloServerClient.prototype.recv_helloString = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new HelloServer_helloString_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'helloString failed: unknown result';
};
HelloServerClient.prototype.helloInt = function(num) {
  this.send_helloInt(num);
  return this.recv_helloInt();
};

HelloServerClient.prototype.send_helloInt = function(num) {
  this.output.writeMessageBegin('helloInt', Thrift.MessageType.CALL, this.seqid);
  var args = new HelloServer_helloInt_args();
  args.num = num;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

HelloServerClient.prototype.recv_helloInt = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new HelloServer_helloInt_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'helloInt failed: unknown result';
};
HelloServerClient.prototype.helloBoolean = function(flag) {
  this.send_helloBoolean(flag);
  return this.recv_helloBoolean();
};

HelloServerClient.prototype.send_helloBoolean = function(flag) {
  this.output.writeMessageBegin('helloBoolean', Thrift.MessageType.CALL, this.seqid);
  var args = new HelloServer_helloBoolean_args();
  args.flag = flag;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

HelloServerClient.prototype.recv_helloBoolean = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new HelloServer_helloBoolean_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'helloBoolean failed: unknown result';
};
HelloServerClient.prototype.helloVoid = function() {
  this.send_helloVoid();
  this.recv_helloVoid();
};

HelloServerClient.prototype.send_helloVoid = function() {
  this.output.writeMessageBegin('helloVoid', Thrift.MessageType.CALL, this.seqid);
  var args = new HelloServer_helloVoid_args();
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

HelloServerClient.prototype.recv_helloVoid = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new HelloServer_helloVoid_result();
  result.read(this.input);
  this.input.readMessageEnd();

  return;
};
HelloServerClient.prototype.helloNull = function() {
  this.send_helloNull();
  return this.recv_helloNull();
};

HelloServerClient.prototype.send_helloNull = function() {
  this.output.writeMessageBegin('helloNull', Thrift.MessageType.CALL, this.seqid);
  var args = new HelloServer_helloNull_args();
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

HelloServerClient.prototype.recv_helloNull = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new HelloServer_helloNull_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'helloNull failed: unknown result';
};
HelloServerClient.prototype.request = function(request) {
  this.send_request(request);
  return this.recv_request();
};

HelloServerClient.prototype.send_request = function(request) {
  this.output.writeMessageBegin('request', Thrift.MessageType.CALL, this.seqid);
  var args = new HelloServer_request_args();
  args.request = request;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

HelloServerClient.prototype.recv_request = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new HelloServer_request_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'request failed: unknown result';
};
