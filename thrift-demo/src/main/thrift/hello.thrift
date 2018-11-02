/**
 * The first thing to know about are types. The available types in Thrift are:
 *
 *  bool        Boolean, one byte
 *  byte        Signed byte
 *  i16         Signed 16-bit integer
 *  i32         Signed 32-bit integer
 *  i64         Signed 64-bit integer
 *  double      64-bit floating point value
 *  string      String
 *  binary      Blob (byte array)
 *  map<t1,t2>  Map from one type to another
 *  list<t1>    Ordered list of one type
 *  set<t1>     Set of unique elements of one type
 *
 * Did you also notice that Thrift supports C style comments?
 */
namespace java service.demo

enum EnumObj{
    FOO=1
    BAR
}

struct FirstRequest{
    1: optional i32 number;
    2: required string msg;
    3: optional map<string,string> mapObj;
    4: optional list<i32> listObj;
    5: optional binary binaryObj;
    6: optional double doubleObj;
    7: optional EnumObj enumObj;
}

struct FirstResponse{
    1: optional i32 number;
    2: optional string msg;
}

service HelloServer{

  string helloString(1:string msg)

  i32 helloInt(1:i32 num)

  bool helloBoolean(1:bool flag)

  void helloVoid()

  string helloNull()

  FirstResponse request(1:required FirstRequest request)
}


