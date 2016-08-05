<?php

/**
 * Created by IntelliJ IDEA.
 * User: wangjun
 * Date: 16/7/28
 * Time: 下午2:07
 */
class User
{

    public $id;
    public $name;
    private $age;
    public $like;

    public function __construct($id, $name, $age)
    {
        $this->id = $id;
        $this->name = $name;
        $this->age = $age;
        $this->like = array("Volvo", "BMW", "Toyota");
    }


}

$user = new User(1, 'wj', '26');
echo json_encode($user) . "\n";

/**
 * @param $json
 * @return User
 */
function getUser($json)
{
    $user02 = json_decode($json);
    return $user02;
}

$user03 = getUser(json_encode($user));

// 这里是可以被反序列化成对象的
echo $user03->id;
echo $user03->name;
// 支持数组 太好了
echo json_encode($user03->like);


