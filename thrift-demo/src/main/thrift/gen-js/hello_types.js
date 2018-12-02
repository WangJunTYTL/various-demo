//
// Autogenerated by Thrift Compiler (0.8.0)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//

EnumObj = {
'FOO' : 1,
'BAR' : 2
};
FirstRequest = function(args) {
  this.number = null;
  this.msg = null;
  this.mapObj = null;
  this.listObj = null;
  this.binaryObj = null;
  this.doubleObj = null;
  this.enumObj = null;
  if (args) {
    if (args.number !== undefined) {
      this.number = args.number;
    }
    if (args.msg !== undefined) {
      this.msg = args.msg;
    }
    if (args.mapObj !== undefined) {
      this.mapObj = args.mapObj;
    }
    if (args.listObj !== undefined) {
      this.listObj = args.listObj;
    }
    if (args.binaryObj !== undefined) {
      this.binaryObj = args.binaryObj;
    }
    if (args.doubleObj !== undefined) {
      this.doubleObj = args.doubleObj;
    }
    if (args.enumObj !== undefined) {
      this.enumObj = args.enumObj;
    }
  }
};
FirstRequest.prototype = {};
FirstRequest.prototype.read = function(input) {
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
        this.number = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.msg = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.MAP) {
        var _size0 = 0;
        var _rtmp34;
        this.mapObj = {};
        var _ktype1 = 0;
        var _vtype2 = 0;
        _rtmp34 = input.readMapBegin();
        _ktype1 = _rtmp34.ktype;
        _vtype2 = _rtmp34.vtype;
        _size0 = _rtmp34.size;
        for (var _i5 = 0; _i5 < _size0; ++_i5)
        {
          if (_i5 > 0 ) {
            if (input.rstack.length > input.rpos[input.rpos.length -1] + 1) {
              input.rstack.pop();
            }
          }
          var key6 = null;
          var val7 = null;
          key6 = input.readString().value;
          val7 = input.readString().value;
          this.mapObj[key6] = val7;
        }
        input.readMapEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.LIST) {
        var _size8 = 0;
        var _rtmp312;
        this.listObj = [];
        var _etype11 = 0;
        _rtmp312 = input.readListBegin();
        _etype11 = _rtmp312.etype;
        _size8 = _rtmp312.size;
        for (var _i13 = 0; _i13 < _size8; ++_i13)
        {
          var elem14 = null;
          elem14 = input.readI32().value;
          this.listObj.push(elem14);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.STRING) {
        this.binaryObj = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.DOUBLE) {
        this.doubleObj = input.readDouble().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.I32) {
        this.enumObj = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

FirstRequest.prototype.write = function(output) {
  output.writeStructBegin('FirstRequest');
  if (this.number) {
    output.writeFieldBegin('number', Thrift.Type.I32, 1);
    output.writeI32(this.number);
    output.writeFieldEnd();
  }
  if (this.msg) {
    output.writeFieldBegin('msg', Thrift.Type.STRING, 2);
    output.writeString(this.msg);
    output.writeFieldEnd();
  }
  if (this.mapObj) {
    output.writeFieldBegin('mapObj', Thrift.Type.MAP, 3);
    output.writeMapBegin(Thrift.Type.STRING, Thrift.Type.STRING, Thrift.objectLength(this.mapObj));
    for (var kiter15 in this.mapObj)
    {
      if (this.mapObj.hasOwnProperty(kiter15))
      {
        var viter16 = this.mapObj[kiter15];
        output.writeString(kiter15);
        output.writeString(viter16);
      }
    }
    output.writeMapEnd();
    output.writeFieldEnd();
  }
  if (this.listObj) {
    output.writeFieldBegin('listObj', Thrift.Type.LIST, 4);
    output.writeListBegin(Thrift.Type.I32, this.listObj.length);
    for (var iter17 in this.listObj)
    {
      if (this.listObj.hasOwnProperty(iter17))
      {
        iter17 = this.listObj[iter17];
        output.writeI32(iter17);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  if (this.binaryObj) {
    output.writeFieldBegin('binaryObj', Thrift.Type.STRING, 5);
    output.writeString(this.binaryObj);
    output.writeFieldEnd();
  }
  if (this.doubleObj) {
    output.writeFieldBegin('doubleObj', Thrift.Type.DOUBLE, 6);
    output.writeDouble(this.doubleObj);
    output.writeFieldEnd();
  }
  if (this.enumObj) {
    output.writeFieldBegin('enumObj', Thrift.Type.I32, 7);
    output.writeI32(this.enumObj);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

FirstResponse = function(args) {
  this.number = null;
  this.msg = null;
  if (args) {
    if (args.number !== undefined) {
      this.number = args.number;
    }
    if (args.msg !== undefined) {
      this.msg = args.msg;
    }
  }
};
FirstResponse.prototype = {};
FirstResponse.prototype.read = function(input) {
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
        this.number = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.msg = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

FirstResponse.prototype.write = function(output) {
  output.writeStructBegin('FirstResponse');
  if (this.number) {
    output.writeFieldBegin('number', Thrift.Type.I32, 1);
    output.writeI32(this.number);
    output.writeFieldEnd();
  }
  if (this.msg) {
    output.writeFieldBegin('msg', Thrift.Type.STRING, 2);
    output.writeString(this.msg);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

