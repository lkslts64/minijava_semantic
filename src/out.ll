@.BT_vtable = global [1 x i8*] [  i8* bitcast (i32 (i8*)* @BT.Start to i8*)]
@.BinaryTree_vtable = global [0 x i8*] []
 @.Tree_vtable = global [20 x i8*] [  i8* bitcast (i1 (i8*,i32)* @Tree.Init to i8*), i8* bitcast (i1 (i8*,i8*)* @Tree.SetRight to i8*), i8* bitcast (i1 (i8*,i8*)* @Tree.SetLeft to i8*), i8* bitcast (i8* (i8*)* @Tree.GetRight to i8*), i8* bitcast (i8* (i8*)* @Tree.GetLeft to i8*), i8* bitcast (i32 (i8*)* @Tree.GetKey to i8*), i8* bitcast (i1 (i8*,i32)* @Tree.SetKey to i8*), i8* bitcast (i1 (i8*)* @Tree.GetHas_Right to i8*), i8* bitcast (i1 (i8*)* @Tree.GetHas_Left to i8*), i8* bitcast (i1 (i8*,i1)* @Tree.SetHas_Left to i8*), i8* bitcast (i1 (i8*,i1)* @Tree.SetHas_Right to i8*), i8* bitcast (i1 (i8*,i32,i32)* @Tree.Compare to i8*), i8* bitcast (i1 (i8*,i32)* @Tree.Insert to i8*), i8* bitcast (i1 (i8*,i32)* @Tree.Delete to i8*), i8* bitcast (i1 (i8*,i8*,i8*)* @Tree.Remove to i8*), i8* bitcast (i1 (i8*,i8*,i8*)* @Tree.RemoveRight to i8*), i8* bitcast (i1 (i8*,i8*,i8*)* @Tree.RemoveLeft to i8*), i8* bitcast (i32 (i8*,i32)* @Tree.Search to i8*), i8* bitcast (i1 (i8*)* @Tree.Print to i8*), i8* bitcast (i1 (i8*,i8*)* @Tree.RecPrint to i8*)]
declare i8* @calloc(i32, i32)
declare i32 @printf(i8*, ...)
declare void @exit(i32)
@_cint = constant [4 x i8] c"%d\0a\00"
@_cOOB = constant [15 x i8] c"Out of bounds\0a\00"
define void @print_int(i32 %i) {
    %_str = bitcast [4 x i8]* @_cint to i8*
    call i32 (i8*, ...) @printf(i8* %_str, i32 %i)
    ret void
}
define void @throw_oob() {
    %_str = bitcast [15 x i8]* @_cOOB to i8*
    call i32 (i8*, ...) @printf(i8* %_str)
    call void @exit(i32 1)
    ret void
}
define i32 @main() {
%_0 = call i8* @calloc(i32 1,i32 8)
%_1 = bitcast i8* %_0 to [1 x i8*]**
store [1 x i8*]* @.BT_vtable, [1 x i8*]** %_1
%_2 = bitcast i8* %_0 to i8***
%_3 = load i8 **, i8*** %_2
%_4 = getelementptr i8*, i8** %_3,i32 0
%_5 = load i8*, i8** %_4
%_6 = bitcast i8* %_5 to i32 (i8*)*
%_7 = call i32 %_6(i8* %_0)
call void (i32) @print_int(i32 %_7)
ret i32 0
}
define i32 @BT.Start(i8* %this) {
%root = alloca i8*
%ntb = alloca i1
%nti = alloca i32
%_0 = call i8* @calloc(i32 1,i32 38)
%_1 = bitcast i8* %_0 to [20 x i8*]**
store [20 x i8*]* @.Tree_vtable, [20 x i8*]** %_1
store i8* %_0, i8** %root
%_2 = load i8*, i8** %root
%_3 = bitcast i8* %_2 to i8***
%_4 = load i8 **, i8*** %_3
%_5 = getelementptr i8*, i8** %_4,i32 0
%_6 = load i8*, i8** %_5
%_7 = bitcast i8* %_6 to i1 (i8*,i32)*
%_8 = call i1 %_7(i8* %_2,i32 16)
store i1 %_8, i1* %ntb
%_9 = load i8*, i8** %root
%_10 = bitcast i8* %_9 to i8***
%_11 = load i8 **, i8*** %_10
%_12 = getelementptr i8*, i8** %_11,i32 18
%_13 = load i8*, i8** %_12
%_14 = bitcast i8* %_13 to i1 (i8*)*
%_15 = call i1 %_14(i8* %_9)
store i1 %_15, i1* %ntb
call void (i32) @print_int(i32 100000000)
%_16 = load i8*, i8** %root
%_17 = bitcast i8* %_16 to i8***
%_18 = load i8 **, i8*** %_17
%_19 = getelementptr i8*, i8** %_18,i32 12
%_20 = load i8*, i8** %_19
%_21 = bitcast i8* %_20 to i1 (i8*,i32)*
%_22 = call i1 %_21(i8* %_16,i32 8)
store i1 %_22, i1* %ntb
%_23 = load i8*, i8** %root
%_24 = bitcast i8* %_23 to i8***
%_25 = load i8 **, i8*** %_24
%_26 = getelementptr i8*, i8** %_25,i32 18
%_27 = load i8*, i8** %_26
%_28 = bitcast i8* %_27 to i1 (i8*)*
%_29 = call i1 %_28(i8* %_23)
store i1 %_29, i1* %ntb
%_30 = load i8*, i8** %root
%_31 = bitcast i8* %_30 to i8***
%_32 = load i8 **, i8*** %_31
%_33 = getelementptr i8*, i8** %_32,i32 12
%_34 = load i8*, i8** %_33
%_35 = bitcast i8* %_34 to i1 (i8*,i32)*
%_36 = call i1 %_35(i8* %_30,i32 24)
store i1 %_36, i1* %ntb
%_37 = load i8*, i8** %root
%_38 = bitcast i8* %_37 to i8***
%_39 = load i8 **, i8*** %_38
%_40 = getelementptr i8*, i8** %_39,i32 12
%_41 = load i8*, i8** %_40
%_42 = bitcast i8* %_41 to i1 (i8*,i32)*
%_43 = call i1 %_42(i8* %_37,i32 4)
store i1 %_43, i1* %ntb
%_44 = load i8*, i8** %root
%_45 = bitcast i8* %_44 to i8***
%_46 = load i8 **, i8*** %_45
%_47 = getelementptr i8*, i8** %_46,i32 12
%_48 = load i8*, i8** %_47
%_49 = bitcast i8* %_48 to i1 (i8*,i32)*
%_50 = call i1 %_49(i8* %_44,i32 12)
store i1 %_50, i1* %ntb
%_51 = load i8*, i8** %root
%_52 = bitcast i8* %_51 to i8***
%_53 = load i8 **, i8*** %_52
%_54 = getelementptr i8*, i8** %_53,i32 12
%_55 = load i8*, i8** %_54
%_56 = bitcast i8* %_55 to i1 (i8*,i32)*
%_57 = call i1 %_56(i8* %_51,i32 20)
store i1 %_57, i1* %ntb
%_58 = load i8*, i8** %root
%_59 = bitcast i8* %_58 to i8***
%_60 = load i8 **, i8*** %_59
%_61 = getelementptr i8*, i8** %_60,i32 12
%_62 = load i8*, i8** %_61
%_63 = bitcast i8* %_62 to i1 (i8*,i32)*
%_64 = call i1 %_63(i8* %_58,i32 28)
store i1 %_64, i1* %ntb
%_65 = load i8*, i8** %root
%_66 = bitcast i8* %_65 to i8***
%_67 = load i8 **, i8*** %_66
%_68 = getelementptr i8*, i8** %_67,i32 12
%_69 = load i8*, i8** %_68
%_70 = bitcast i8* %_69 to i1 (i8*,i32)*
%_71 = call i1 %_70(i8* %_65,i32 14)
store i1 %_71, i1* %ntb
%_72 = load i8*, i8** %root
%_73 = bitcast i8* %_72 to i8***
%_74 = load i8 **, i8*** %_73
%_75 = getelementptr i8*, i8** %_74,i32 18
%_76 = load i8*, i8** %_75
%_77 = bitcast i8* %_76 to i1 (i8*)*
%_78 = call i1 %_77(i8* %_72)
store i1 %_78, i1* %ntb
%_79 = load i8*, i8** %root
%_80 = bitcast i8* %_79 to i8***
%_81 = load i8 **, i8*** %_80
%_82 = getelementptr i8*, i8** %_81,i32 17
%_83 = load i8*, i8** %_82
%_84 = bitcast i8* %_83 to i32 (i8*,i32)*
%_85 = call i32 %_84(i8* %_79,i32 24)
call void (i32) @print_int(i32 %_85)
%_86 = load i8*, i8** %root
%_87 = bitcast i8* %_86 to i8***
%_88 = load i8 **, i8*** %_87
%_89 = getelementptr i8*, i8** %_88,i32 17
%_90 = load i8*, i8** %_89
%_91 = bitcast i8* %_90 to i32 (i8*,i32)*
%_92 = call i32 %_91(i8* %_86,i32 12)
call void (i32) @print_int(i32 %_92)
%_93 = load i8*, i8** %root
%_94 = bitcast i8* %_93 to i8***
%_95 = load i8 **, i8*** %_94
%_96 = getelementptr i8*, i8** %_95,i32 17
%_97 = load i8*, i8** %_96
%_98 = bitcast i8* %_97 to i32 (i8*,i32)*
%_99 = call i32 %_98(i8* %_93,i32 16)
call void (i32) @print_int(i32 %_99)
%_100 = load i8*, i8** %root
%_101 = bitcast i8* %_100 to i8***
%_102 = load i8 **, i8*** %_101
%_103 = getelementptr i8*, i8** %_102,i32 17
%_104 = load i8*, i8** %_103
%_105 = bitcast i8* %_104 to i32 (i8*,i32)*
%_106 = call i32 %_105(i8* %_100,i32 50)
call void (i32) @print_int(i32 %_106)
%_107 = load i8*, i8** %root
%_108 = bitcast i8* %_107 to i8***
%_109 = load i8 **, i8*** %_108
%_110 = getelementptr i8*, i8** %_109,i32 17
%_111 = load i8*, i8** %_110
%_112 = bitcast i8* %_111 to i32 (i8*,i32)*
%_113 = call i32 %_112(i8* %_107,i32 12)
call void (i32) @print_int(i32 %_113)
%_114 = load i8*, i8** %root
%_115 = bitcast i8* %_114 to i8***
%_116 = load i8 **, i8*** %_115
%_117 = getelementptr i8*, i8** %_116,i32 13
%_118 = load i8*, i8** %_117
%_119 = bitcast i8* %_118 to i1 (i8*,i32)*
%_120 = call i1 %_119(i8* %_114,i32 12)
store i1 %_120, i1* %ntb
%_121 = load i8*, i8** %root
%_122 = bitcast i8* %_121 to i8***
%_123 = load i8 **, i8*** %_122
%_124 = getelementptr i8*, i8** %_123,i32 18
%_125 = load i8*, i8** %_124
%_126 = bitcast i8* %_125 to i1 (i8*)*
%_127 = call i1 %_126(i8* %_121)
store i1 %_127, i1* %ntb
%_128 = load i8*, i8** %root
%_129 = bitcast i8* %_128 to i8***
%_130 = load i8 **, i8*** %_129
%_131 = getelementptr i8*, i8** %_130,i32 17
%_132 = load i8*, i8** %_131
%_133 = bitcast i8* %_132 to i32 (i8*,i32)*
%_134 = call i32 %_133(i8* %_128,i32 12)
call void (i32) @print_int(i32 %_134)
ret i32 0
}
define i1 @Tree.Init(i8* %this,i32 %.v_key) {
%v_key = alloca i32
store i32 %.v_key, i32* %v_key
%_0 = getelementptr i8, i8* %this,i32 24
%_1 = bitcast i8* %_0 to i32*
%_2 = load i32, i32* %v_key
store i32 %_2, i32* %_1
%_3 = getelementptr i8, i8* %this,i32 28
%_4 = bitcast i8* %_3 to i1*
store i1 0, i1* %_4
%_5 = getelementptr i8, i8* %this,i32 29
%_6 = bitcast i8* %_5 to i1*
store i1 0, i1* %_6
ret i1 1
}
define i1 @Tree.SetRight(i8* %this,i8* %.rn) {
%rn = alloca i8*
store i8* %.rn, i8** %rn
%_0 = getelementptr i8, i8* %this,i32 16
%_1 = bitcast i8* %_0 to i8**
%_2 = load i8*, i8** %rn
store i8* %_2, i8** %_1
ret i1 1
}
define i1 @Tree.SetLeft(i8* %this,i8* %.ln) {
%ln = alloca i8*
store i8* %.ln, i8** %ln
%_0 = getelementptr i8, i8* %this,i32 8
%_1 = bitcast i8* %_0 to i8**
%_2 = load i8*, i8** %ln
store i8* %_2, i8** %_1
ret i1 1
}
define i8* @Tree.GetRight(i8* %this) {
%_1 = getelementptr i8, i8* %this,i32 16
%_2 = bitcast i8* %_1 to i8**
%_0 = load i8*, i8** %_2
ret i8* %_0
}
define i8* @Tree.GetLeft(i8* %this) {
%_1 = getelementptr i8, i8* %this,i32 8
%_2 = bitcast i8* %_1 to i8**
%_0 = load i8*, i8** %_2
ret i8* %_0
}
define i32 @Tree.GetKey(i8* %this) {
%_1 = getelementptr i8, i8* %this,i32 24
%_2 = bitcast i8* %_1 to i32*
%_0 = load i32, i32* %_2
ret i32 %_0
}
define i1 @Tree.SetKey(i8* %this,i32 %.v_key) {
%v_key = alloca i32
store i32 %.v_key, i32* %v_key
%_0 = getelementptr i8, i8* %this,i32 24
%_1 = bitcast i8* %_0 to i32*
%_2 = load i32, i32* %v_key
store i32 %_2, i32* %_1
ret i1 1
}
define i1 @Tree.GetHas_Right(i8* %this) {
%_1 = getelementptr i8, i8* %this,i32 29
%_2 = bitcast i8* %_1 to i1*
%_0 = load i1, i1* %_2
ret i1 %_0
}
define i1 @Tree.GetHas_Left(i8* %this) {
%_1 = getelementptr i8, i8* %this,i32 28
%_2 = bitcast i8* %_1 to i1*
%_0 = load i1, i1* %_2
ret i1 %_0
}
define i1 @Tree.SetHas_Left(i8* %this,i1 %.val) {
%val = alloca i1
store i1 %.val, i1* %val
%_0 = getelementptr i8, i8* %this,i32 28
%_1 = bitcast i8* %_0 to i1*
%_2 = load i1, i1* %val
store i1 %_2, i1* %_1
ret i1 1
}
define i1 @Tree.SetHas_Right(i8* %this,i1 %.val) {
%val = alloca i1
store i1 %.val, i1* %val
%_0 = getelementptr i8, i8* %this,i32 29
%_1 = bitcast i8* %_0 to i1*
%_2 = load i1, i1* %val
store i1 %_2, i1* %_1
ret i1 1
}
define i1 @Tree.Compare(i8* %this,i32 %.num1,i32 %.num2) {
%num1 = alloca i32
store i32 %.num1, i32* %num1
%num2 = alloca i32
store i32 %.num2, i32* %num2
%ntb = alloca i1
%nti = alloca i32
store i1 0, i1* %ntb
%_0 = load i32, i32* %num2
%_1 = add i32 %_0, 1
store i32 %_1, i32* %nti
%_2 = load i32, i32* %num1
%_3 = load i32, i32* %num2
%_4 = icmp slt i32 %_2, %_3
br i1 %_4, label %if0, label %if1
if0:
store i1 0, i1* %ntb
br label %if2
if1:
%_5 = load i32, i32* %num1
%_6 = load i32, i32* %nti
%_7 = icmp slt i32 %_5, %_6
%_8 = icmp eq i1 %_7, 0
br i1 %_8, label %if3, label %if4
if3:
store i1 0, i1* %ntb
br label %if5
if4:
store i1 1, i1* %ntb
br label %if5
if5:
br label %if2
if2:
%_9 = load i1, i1* %ntb
ret i1 %_9
}
define i1 @Tree.Insert(i8* %this,i32 %.v_key) {
%v_key = alloca i32
store i32 %.v_key, i32* %v_key
%new_node = alloca i8*
%ntb = alloca i1
%cont = alloca i1
%key_aux = alloca i32
%current_node = alloca i8*
%_0 = call i8* @calloc(i32 1,i32 38)
%_1 = bitcast i8* %_0 to [20 x i8*]**
store [20 x i8*]* @.Tree_vtable, [20 x i8*]** %_1
store i8* %_0, i8** %new_node
%_2 = load i8*, i8** %new_node
%_3 = bitcast i8* %_2 to i8***
%_4 = load i8 **, i8*** %_3
%_5 = getelementptr i8*, i8** %_4,i32 0
%_6 = load i8*, i8** %_5
%_7 = bitcast i8* %_6 to i1 (i8*,i32)*
%_8 = load i32, i32* %v_key
%_9 = call i1 %_7(i8* %_2,i32 %_8)
store i1 %_9, i1* %ntb
store i8* %this, i8** %current_node
store i1 1, i1* %cont
br label %loop0
loop0:
%_10 = load i1, i1* %cont
br i1 %_10, label %loop1, label %loop2
loop1:
%_11 = load i8*, i8** %current_node
%_12 = bitcast i8* %_11 to i8***
%_13 = load i8 **, i8*** %_12
%_14 = getelementptr i8*, i8** %_13,i32 5
%_15 = load i8*, i8** %_14
%_16 = bitcast i8* %_15 to i32 (i8*)*
%_17 = call i32 %_16(i8* %_11)
store i32 %_17, i32* %key_aux
%_18 = load i32, i32* %v_key
%_19 = load i32, i32* %key_aux
%_20 = icmp slt i32 %_18, %_19
br i1 %_20, label %if6, label %if7
if6:
%_21 = load i8*, i8** %current_node
%_22 = bitcast i8* %_21 to i8***
%_23 = load i8 **, i8*** %_22
%_24 = getelementptr i8*, i8** %_23,i32 8
%_25 = load i8*, i8** %_24
%_26 = bitcast i8* %_25 to i1 (i8*)*
%_27 = call i1 %_26(i8* %_21)
br i1 %_27, label %if9, label %if10
if9:
%_28 = load i8*, i8** %current_node
%_29 = bitcast i8* %_28 to i8***
%_30 = load i8 **, i8*** %_29
%_31 = getelementptr i8*, i8** %_30,i32 4
%_32 = load i8*, i8** %_31
%_33 = bitcast i8* %_32 to i8* (i8*)*
%_34 = call i8* %_33(i8* %_28)
store i8* %_34, i8** %current_node
br label %if11
if10:
store i1 0, i1* %cont
%_35 = load i8*, i8** %current_node
%_36 = bitcast i8* %_35 to i8***
%_37 = load i8 **, i8*** %_36
%_38 = getelementptr i8*, i8** %_37,i32 9
%_39 = load i8*, i8** %_38
%_40 = bitcast i8* %_39 to i1 (i8*,i1)*
%_41 = call i1 %_40(i8* %_35,i1 1)
store i1 %_41, i1* %ntb
%_42 = load i8*, i8** %current_node
%_43 = bitcast i8* %_42 to i8***
%_44 = load i8 **, i8*** %_43
%_45 = getelementptr i8*, i8** %_44,i32 2
%_46 = load i8*, i8** %_45
%_47 = bitcast i8* %_46 to i1 (i8*,i8*)*
%_48 = load i8*, i8** %new_node
%_49 = call i1 %_47(i8* %_42,i8* %_48)
store i1 %_49, i1* %ntb
br label %if11
if11:
br label %if8
if7:
%_50 = load i8*, i8** %current_node
%_51 = bitcast i8* %_50 to i8***
%_52 = load i8 **, i8*** %_51
%_53 = getelementptr i8*, i8** %_52,i32 7
%_54 = load i8*, i8** %_53
%_55 = bitcast i8* %_54 to i1 (i8*)*
%_56 = call i1 %_55(i8* %_50)
br i1 %_56, label %if12, label %if13
if12:
%_57 = load i8*, i8** %current_node
%_58 = bitcast i8* %_57 to i8***
%_59 = load i8 **, i8*** %_58
%_60 = getelementptr i8*, i8** %_59,i32 3
%_61 = load i8*, i8** %_60
%_62 = bitcast i8* %_61 to i8* (i8*)*
%_63 = call i8* %_62(i8* %_57)
store i8* %_63, i8** %current_node
br label %if14
if13:
store i1 0, i1* %cont
%_64 = load i8*, i8** %current_node
%_65 = bitcast i8* %_64 to i8***
%_66 = load i8 **, i8*** %_65
%_67 = getelementptr i8*, i8** %_66,i32 10
%_68 = load i8*, i8** %_67
%_69 = bitcast i8* %_68 to i1 (i8*,i1)*
%_70 = call i1 %_69(i8* %_64,i1 1)
store i1 %_70, i1* %ntb
%_71 = load i8*, i8** %current_node
%_72 = bitcast i8* %_71 to i8***
%_73 = load i8 **, i8*** %_72
%_74 = getelementptr i8*, i8** %_73,i32 1
%_75 = load i8*, i8** %_74
%_76 = bitcast i8* %_75 to i1 (i8*,i8*)*
%_77 = load i8*, i8** %new_node
%_78 = call i1 %_76(i8* %_71,i8* %_77)
store i1 %_78, i1* %ntb
br label %if14
if14:
br label %if8
if8:
br label %loop0
loop2:
ret i1 1
}
define i1 @Tree.Delete(i8* %this,i32 %.v_key) {
%v_key = alloca i32
store i32 %.v_key, i32* %v_key
%current_node = alloca i8*
%parent_node = alloca i8*
%cont = alloca i1
%found = alloca i1
%is_root = alloca i1
%key_aux = alloca i32
%ntb = alloca i1
store i8* %this, i8** %current_node
store i8* %this, i8** %parent_node
store i1 1, i1* %cont
store i1 0, i1* %found
store i1 1, i1* %is_root
br label %loop3
loop3:
%_0 = load i1, i1* %cont
br i1 %_0, label %loop4, label %loop5
loop4:
%_1 = load i8*, i8** %current_node
%_2 = bitcast i8* %_1 to i8***
%_3 = load i8 **, i8*** %_2
%_4 = getelementptr i8*, i8** %_3,i32 5
%_5 = load i8*, i8** %_4
%_6 = bitcast i8* %_5 to i32 (i8*)*
%_7 = call i32 %_6(i8* %_1)
store i32 %_7, i32* %key_aux
%_8 = load i32, i32* %v_key
%_9 = load i32, i32* %key_aux
%_10 = icmp slt i32 %_8, %_9
br i1 %_10, label %if15, label %if16
if15:
%_11 = load i8*, i8** %current_node
%_12 = bitcast i8* %_11 to i8***
%_13 = load i8 **, i8*** %_12
%_14 = getelementptr i8*, i8** %_13,i32 8
%_15 = load i8*, i8** %_14
%_16 = bitcast i8* %_15 to i1 (i8*)*
%_17 = call i1 %_16(i8* %_11)
br i1 %_17, label %if18, label %if19
if18:
%_18 = load i8*, i8** %current_node
store i8* %_18, i8** %parent_node
%_19 = load i8*, i8** %current_node
%_20 = bitcast i8* %_19 to i8***
%_21 = load i8 **, i8*** %_20
%_22 = getelementptr i8*, i8** %_21,i32 4
%_23 = load i8*, i8** %_22
%_24 = bitcast i8* %_23 to i8* (i8*)*
%_25 = call i8* %_24(i8* %_19)
store i8* %_25, i8** %current_node
br label %if20
if19:
store i1 0, i1* %cont
br label %if20
if20:
br label %if17
if16:
%_26 = load i32, i32* %key_aux
%_27 = load i32, i32* %v_key
%_28 = icmp slt i32 %_26, %_27
br i1 %_28, label %if21, label %if22
if21:
%_29 = load i8*, i8** %current_node
%_30 = bitcast i8* %_29 to i8***
%_31 = load i8 **, i8*** %_30
%_32 = getelementptr i8*, i8** %_31,i32 7
%_33 = load i8*, i8** %_32
%_34 = bitcast i8* %_33 to i1 (i8*)*
%_35 = call i1 %_34(i8* %_29)
br i1 %_35, label %if24, label %if25
if24:
%_36 = load i8*, i8** %current_node
store i8* %_36, i8** %parent_node
%_37 = load i8*, i8** %current_node
%_38 = bitcast i8* %_37 to i8***
%_39 = load i8 **, i8*** %_38
%_40 = getelementptr i8*, i8** %_39,i32 3
%_41 = load i8*, i8** %_40
%_42 = bitcast i8* %_41 to i8* (i8*)*
%_43 = call i8* %_42(i8* %_37)
store i8* %_43, i8** %current_node
br label %if26
if25:
store i1 0, i1* %cont
br label %if26
if26:
br label %if23
if22:
%_44 = load i1, i1* %is_root
br i1 %_44, label %if27, label %if28
if27:
%_47 = load i8*, i8** %current_node
%_48 = bitcast i8* %_47 to i8***
%_49 = load i8 **, i8*** %_48
%_50 = getelementptr i8*, i8** %_49,i32 7
%_51 = load i8*, i8** %_50
%_52 = bitcast i8* %_51 to i1 (i8*)*
%_53 = call i1 %_52(i8* %_47)
%_54 = icmp eq i1 %_53, 0
br i1 %_54, label %if33,label %if34
if33:
%_55 = load i8*, i8** %current_node
%_56 = bitcast i8* %_55 to i8***
%_57 = load i8 **, i8*** %_56
%_58 = getelementptr i8*, i8** %_57,i32 8
%_59 = load i8*, i8** %_58
%_60 = bitcast i8* %_59 to i1 (i8*)*
%_61 = call i1 %_60(i8* %_55)
%_62 = icmp eq i1 %_61, 0
br label %if35
if34:
%_45 = icmp eq i1 %_54, 1
br label %if35
if35:
%_46 = phi i1 [%_62, %if33], [%_45, %if34]
br i1 %_46, label %if30, label %if31
if30:
store i1 1, i1* %ntb
br label %if32
if31:
%_63 = bitcast i8* %this to i8***
%_64 = load i8 **, i8*** %_63
%_65 = getelementptr i8*, i8** %_64,i32 14
%_66 = load i8*, i8** %_65
%_67 = bitcast i8* %_66 to i1 (i8*,i8*,i8*)*
%_68 = load i8*, i8** %parent_node
%_69 = load i8*, i8** %current_node
%_70 = call i1 %_67(i8* %this,i8* %_68,i8* %_69)
store i1 %_70, i1* %ntb
br label %if32
if32:
br label %if29
if28:
%_71 = bitcast i8* %this to i8***
%_72 = load i8 **, i8*** %_71
%_73 = getelementptr i8*, i8** %_72,i32 14
%_74 = load i8*, i8** %_73
%_75 = bitcast i8* %_74 to i1 (i8*,i8*,i8*)*
%_76 = load i8*, i8** %parent_node
%_77 = load i8*, i8** %current_node
%_78 = call i1 %_75(i8* %this,i8* %_76,i8* %_77)
store i1 %_78, i1* %ntb
br label %if29
if29:
store i1 1, i1* %found
store i1 0, i1* %cont
br label %if23
if23:
br label %if17
if17:
store i1 0, i1* %is_root
br label %loop3
loop5:
%_79 = load i1, i1* %found
ret i1 %_79
}
define i1 @Tree.Remove(i8* %this,i8* %.p_node,i8* %.c_node) {
%p_node = alloca i8*
store i8* %.p_node, i8** %p_node
%c_node = alloca i8*
store i8* %.c_node, i8** %c_node
%ntb = alloca i1
%auxkey1 = alloca i32
%auxkey2 = alloca i32
%_0 = load i8*, i8** %c_node
%_1 = bitcast i8* %_0 to i8***
%_2 = load i8 **, i8*** %_1
%_3 = getelementptr i8*, i8** %_2,i32 8
%_4 = load i8*, i8** %_3
%_5 = bitcast i8* %_4 to i1 (i8*)*
%_6 = call i1 %_5(i8* %_0)
br i1 %_6, label %if36, label %if37
if36:
%_7 = bitcast i8* %this to i8***
%_8 = load i8 **, i8*** %_7
%_9 = getelementptr i8*, i8** %_8,i32 16
%_10 = load i8*, i8** %_9
%_11 = bitcast i8* %_10 to i1 (i8*,i8*,i8*)*
%_12 = load i8*, i8** %p_node
%_13 = load i8*, i8** %c_node
%_14 = call i1 %_11(i8* %this,i8* %_12,i8* %_13)
store i1 %_14, i1* %ntb
br label %if38
if37:
%_15 = load i8*, i8** %c_node
%_16 = bitcast i8* %_15 to i8***
%_17 = load i8 **, i8*** %_16
%_18 = getelementptr i8*, i8** %_17,i32 7
%_19 = load i8*, i8** %_18
%_20 = bitcast i8* %_19 to i1 (i8*)*
%_21 = call i1 %_20(i8* %_15)
br i1 %_21, label %if39, label %if40
if39:
%_22 = bitcast i8* %this to i8***
%_23 = load i8 **, i8*** %_22
%_24 = getelementptr i8*, i8** %_23,i32 15
%_25 = load i8*, i8** %_24
%_26 = bitcast i8* %_25 to i1 (i8*,i8*,i8*)*
%_27 = load i8*, i8** %p_node
%_28 = load i8*, i8** %c_node
%_29 = call i1 %_26(i8* %this,i8* %_27,i8* %_28)
store i1 %_29, i1* %ntb
br label %if41
if40:
%_30 = load i8*, i8** %c_node
%_31 = bitcast i8* %_30 to i8***
%_32 = load i8 **, i8*** %_31
%_33 = getelementptr i8*, i8** %_32,i32 5
%_34 = load i8*, i8** %_33
%_35 = bitcast i8* %_34 to i32 (i8*)*
%_36 = call i32 %_35(i8* %_30)
store i32 %_36, i32* %auxkey1
%_37 = load i8*, i8** %p_node
%_38 = bitcast i8* %_37 to i8***
%_39 = load i8 **, i8*** %_38
%_40 = getelementptr i8*, i8** %_39,i32 4
%_41 = load i8*, i8** %_40
%_42 = bitcast i8* %_41 to i8* (i8*)*
%_43 = call i8* %_42(i8* %_37)
%_44 = bitcast i8* %_43 to i8***
%_45 = load i8 **, i8*** %_44
%_46 = getelementptr i8*, i8** %_45,i32 5
%_47 = load i8*, i8** %_46
%_48 = bitcast i8* %_47 to i32 (i8*)*
%_49 = call i32 %_48(i8* %_43)
store i32 %_49, i32* %auxkey2
%_50 = bitcast i8* %this to i8***
%_51 = load i8 **, i8*** %_50
%_52 = getelementptr i8*, i8** %_51,i32 11
%_53 = load i8*, i8** %_52
%_54 = bitcast i8* %_53 to i1 (i8*,i32,i32)*
%_55 = load i32, i32* %auxkey1
%_56 = load i32, i32* %auxkey2
%_57 = call i1 %_54(i8* %this,i32 %_55,i32 %_56)
br i1 %_57, label %if42, label %if43
if42:
%_58 = load i8*, i8** %p_node
%_59 = bitcast i8* %_58 to i8***
%_60 = load i8 **, i8*** %_59
%_61 = getelementptr i8*, i8** %_60,i32 2
%_62 = load i8*, i8** %_61
%_63 = bitcast i8* %_62 to i1 (i8*,i8*)*
%_65 = getelementptr i8, i8* %this,i32 30
%_66 = bitcast i8* %_65 to i8**
%_64 = load i8*, i8** %_66
%_67 = call i1 %_63(i8* %_58,i8* %_64)
store i1 %_67, i1* %ntb
%_68 = load i8*, i8** %p_node
%_69 = bitcast i8* %_68 to i8***
%_70 = load i8 **, i8*** %_69
%_71 = getelementptr i8*, i8** %_70,i32 9
%_72 = load i8*, i8** %_71
%_73 = bitcast i8* %_72 to i1 (i8*,i1)*
%_74 = call i1 %_73(i8* %_68,i1 0)
store i1 %_74, i1* %ntb
br label %if44
if43:
%_75 = load i8*, i8** %p_node
%_76 = bitcast i8* %_75 to i8***
%_77 = load i8 **, i8*** %_76
%_78 = getelementptr i8*, i8** %_77,i32 1
%_79 = load i8*, i8** %_78
%_80 = bitcast i8* %_79 to i1 (i8*,i8*)*
%_82 = getelementptr i8, i8* %this,i32 30
%_83 = bitcast i8* %_82 to i8**
%_81 = load i8*, i8** %_83
%_84 = call i1 %_80(i8* %_75,i8* %_81)
store i1 %_84, i1* %ntb
%_85 = load i8*, i8** %p_node
%_86 = bitcast i8* %_85 to i8***
%_87 = load i8 **, i8*** %_86
%_88 = getelementptr i8*, i8** %_87,i32 10
%_89 = load i8*, i8** %_88
%_90 = bitcast i8* %_89 to i1 (i8*,i1)*
%_91 = call i1 %_90(i8* %_85,i1 0)
store i1 %_91, i1* %ntb
br label %if44
if44:
br label %if41
if41:
br label %if38
if38:
ret i1 1
}
define i1 @Tree.RemoveRight(i8* %this,i8* %.p_node,i8* %.c_node) {
%p_node = alloca i8*
store i8* %.p_node, i8** %p_node
%c_node = alloca i8*
store i8* %.c_node, i8** %c_node
%ntb = alloca i1
br label %loop6
loop6:
%_0 = load i8*, i8** %c_node
%_1 = bitcast i8* %_0 to i8***
%_2 = load i8 **, i8*** %_1
%_3 = getelementptr i8*, i8** %_2,i32 7
%_4 = load i8*, i8** %_3
%_5 = bitcast i8* %_4 to i1 (i8*)*
%_6 = call i1 %_5(i8* %_0)
br i1 %_6, label %loop7, label %loop8
loop7:
%_7 = load i8*, i8** %c_node
%_8 = bitcast i8* %_7 to i8***
%_9 = load i8 **, i8*** %_8
%_10 = getelementptr i8*, i8** %_9,i32 6
%_11 = load i8*, i8** %_10
%_12 = bitcast i8* %_11 to i1 (i8*,i32)*
%_13 = load i8*, i8** %c_node
%_14 = bitcast i8* %_13 to i8***
%_15 = load i8 **, i8*** %_14
%_16 = getelementptr i8*, i8** %_15,i32 3
%_17 = load i8*, i8** %_16
%_18 = bitcast i8* %_17 to i8* (i8*)*
%_19 = call i8* %_18(i8* %_13)
%_20 = bitcast i8* %_19 to i8***
%_21 = load i8 **, i8*** %_20
%_22 = getelementptr i8*, i8** %_21,i32 5
%_23 = load i8*, i8** %_22
%_24 = bitcast i8* %_23 to i32 (i8*)*
%_25 = call i32 %_24(i8* %_19)
%_26 = call i1 %_12(i8* %_7,i32 %_25)
store i1 %_26, i1* %ntb
%_27 = load i8*, i8** %c_node
store i8* %_27, i8** %p_node
%_28 = load i8*, i8** %c_node
%_29 = bitcast i8* %_28 to i8***
%_30 = load i8 **, i8*** %_29
%_31 = getelementptr i8*, i8** %_30,i32 3
%_32 = load i8*, i8** %_31
%_33 = bitcast i8* %_32 to i8* (i8*)*
%_34 = call i8* %_33(i8* %_28)
store i8* %_34, i8** %c_node
br label %loop6
loop8:
%_35 = load i8*, i8** %p_node
%_36 = bitcast i8* %_35 to i8***
%_37 = load i8 **, i8*** %_36
%_38 = getelementptr i8*, i8** %_37,i32 1
%_39 = load i8*, i8** %_38
%_40 = bitcast i8* %_39 to i1 (i8*,i8*)*
%_42 = getelementptr i8, i8* %this,i32 30
%_43 = bitcast i8* %_42 to i8**
%_41 = load i8*, i8** %_43
%_44 = call i1 %_40(i8* %_35,i8* %_41)
store i1 %_44, i1* %ntb
%_45 = load i8*, i8** %p_node
%_46 = bitcast i8* %_45 to i8***
%_47 = load i8 **, i8*** %_46
%_48 = getelementptr i8*, i8** %_47,i32 10
%_49 = load i8*, i8** %_48
%_50 = bitcast i8* %_49 to i1 (i8*,i1)*
%_51 = call i1 %_50(i8* %_45,i1 0)
store i1 %_51, i1* %ntb
ret i1 1
}
define i1 @Tree.RemoveLeft(i8* %this,i8* %.p_node,i8* %.c_node) {
%p_node = alloca i8*
store i8* %.p_node, i8** %p_node
%c_node = alloca i8*
store i8* %.c_node, i8** %c_node
%ntb = alloca i1
br label %loop9
loop9:
%_0 = load i8*, i8** %c_node
%_1 = bitcast i8* %_0 to i8***
%_2 = load i8 **, i8*** %_1
%_3 = getelementptr i8*, i8** %_2,i32 8
%_4 = load i8*, i8** %_3
%_5 = bitcast i8* %_4 to i1 (i8*)*
%_6 = call i1 %_5(i8* %_0)
br i1 %_6, label %loop10, label %loop11
loop10:
%_7 = load i8*, i8** %c_node
%_8 = bitcast i8* %_7 to i8***
%_9 = load i8 **, i8*** %_8
%_10 = getelementptr i8*, i8** %_9,i32 6
%_11 = load i8*, i8** %_10
%_12 = bitcast i8* %_11 to i1 (i8*,i32)*
%_13 = load i8*, i8** %c_node
%_14 = bitcast i8* %_13 to i8***
%_15 = load i8 **, i8*** %_14
%_16 = getelementptr i8*, i8** %_15,i32 4
%_17 = load i8*, i8** %_16
%_18 = bitcast i8* %_17 to i8* (i8*)*
%_19 = call i8* %_18(i8* %_13)
%_20 = bitcast i8* %_19 to i8***
%_21 = load i8 **, i8*** %_20
%_22 = getelementptr i8*, i8** %_21,i32 5
%_23 = load i8*, i8** %_22
%_24 = bitcast i8* %_23 to i32 (i8*)*
%_25 = call i32 %_24(i8* %_19)
%_26 = call i1 %_12(i8* %_7,i32 %_25)
store i1 %_26, i1* %ntb
%_27 = load i8*, i8** %c_node
store i8* %_27, i8** %p_node
%_28 = load i8*, i8** %c_node
%_29 = bitcast i8* %_28 to i8***
%_30 = load i8 **, i8*** %_29
%_31 = getelementptr i8*, i8** %_30,i32 4
%_32 = load i8*, i8** %_31
%_33 = bitcast i8* %_32 to i8* (i8*)*
%_34 = call i8* %_33(i8* %_28)
store i8* %_34, i8** %c_node
br label %loop9
loop11:
%_35 = load i8*, i8** %p_node
%_36 = bitcast i8* %_35 to i8***
%_37 = load i8 **, i8*** %_36
%_38 = getelementptr i8*, i8** %_37,i32 2
%_39 = load i8*, i8** %_38
%_40 = bitcast i8* %_39 to i1 (i8*,i8*)*
%_42 = getelementptr i8, i8* %this,i32 30
%_43 = bitcast i8* %_42 to i8**
%_41 = load i8*, i8** %_43
%_44 = call i1 %_40(i8* %_35,i8* %_41)
store i1 %_44, i1* %ntb
%_45 = load i8*, i8** %p_node
%_46 = bitcast i8* %_45 to i8***
%_47 = load i8 **, i8*** %_46
%_48 = getelementptr i8*, i8** %_47,i32 9
%_49 = load i8*, i8** %_48
%_50 = bitcast i8* %_49 to i1 (i8*,i1)*
%_51 = call i1 %_50(i8* %_45,i1 0)
store i1 %_51, i1* %ntb
ret i1 1
}
define i32 @Tree.Search(i8* %this,i32 %.v_key) {
%v_key = alloca i32
store i32 %.v_key, i32* %v_key
%cont = alloca i1
%ifound = alloca i32
%current_node = alloca i8*
%key_aux = alloca i32
store i8* %this, i8** %current_node
store i1 1, i1* %cont
store i32 0, i32* %ifound
br label %loop12
loop12:
%_0 = load i1, i1* %cont
br i1 %_0, label %loop13, label %loop14
loop13:
%_1 = load i8*, i8** %current_node
%_2 = bitcast i8* %_1 to i8***
%_3 = load i8 **, i8*** %_2
%_4 = getelementptr i8*, i8** %_3,i32 5
%_5 = load i8*, i8** %_4
%_6 = bitcast i8* %_5 to i32 (i8*)*
%_7 = call i32 %_6(i8* %_1)
store i32 %_7, i32* %key_aux
%_8 = load i32, i32* %v_key
%_9 = load i32, i32* %key_aux
%_10 = icmp slt i32 %_8, %_9
br i1 %_10, label %if45, label %if46
if45:
%_11 = load i8*, i8** %current_node
%_12 = bitcast i8* %_11 to i8***
%_13 = load i8 **, i8*** %_12
%_14 = getelementptr i8*, i8** %_13,i32 8
%_15 = load i8*, i8** %_14
%_16 = bitcast i8* %_15 to i1 (i8*)*
%_17 = call i1 %_16(i8* %_11)
br i1 %_17, label %if48, label %if49
if48:
%_18 = load i8*, i8** %current_node
%_19 = bitcast i8* %_18 to i8***
%_20 = load i8 **, i8*** %_19
%_21 = getelementptr i8*, i8** %_20,i32 4
%_22 = load i8*, i8** %_21
%_23 = bitcast i8* %_22 to i8* (i8*)*
%_24 = call i8* %_23(i8* %_18)
store i8* %_24, i8** %current_node
br label %if50
if49:
store i1 0, i1* %cont
br label %if50
if50:
br label %if47
if46:
%_25 = load i32, i32* %key_aux
%_26 = load i32, i32* %v_key
%_27 = icmp slt i32 %_25, %_26
br i1 %_27, label %if51, label %if52
if51:
%_28 = load i8*, i8** %current_node
%_29 = bitcast i8* %_28 to i8***
%_30 = load i8 **, i8*** %_29
%_31 = getelementptr i8*, i8** %_30,i32 7
%_32 = load i8*, i8** %_31
%_33 = bitcast i8* %_32 to i1 (i8*)*
%_34 = call i1 %_33(i8* %_28)
br i1 %_34, label %if54, label %if55
if54:
%_35 = load i8*, i8** %current_node
%_36 = bitcast i8* %_35 to i8***
%_37 = load i8 **, i8*** %_36
%_38 = getelementptr i8*, i8** %_37,i32 3
%_39 = load i8*, i8** %_38
%_40 = bitcast i8* %_39 to i8* (i8*)*
%_41 = call i8* %_40(i8* %_35)
store i8* %_41, i8** %current_node
br label %if56
if55:
store i1 0, i1* %cont
br label %if56
if56:
br label %if53
if52:
store i32 1, i32* %ifound
store i1 0, i1* %cont
br label %if53
if53:
br label %if47
if47:
br label %loop12
loop14:
%_42 = load i32, i32* %ifound
ret i32 %_42
}
define i1 @Tree.Print(i8* %this) {
%current_node = alloca i8*
%ntb = alloca i1
store i8* %this, i8** %current_node
%_0 = bitcast i8* %this to i8***
%_1 = load i8 **, i8*** %_0
%_2 = getelementptr i8*, i8** %_1,i32 19
%_3 = load i8*, i8** %_2
%_4 = bitcast i8* %_3 to i1 (i8*,i8*)*
%_5 = load i8*, i8** %current_node
%_6 = call i1 %_4(i8* %this,i8* %_5)
store i1 %_6, i1* %ntb
ret i1 1
}
define i1 @Tree.RecPrint(i8* %this,i8* %.node) {
%node = alloca i8*
store i8* %.node, i8** %node
%ntb = alloca i1
%_0 = load i8*, i8** %node
%_1 = bitcast i8* %_0 to i8***
%_2 = load i8 **, i8*** %_1
%_3 = getelementptr i8*, i8** %_2,i32 8
%_4 = load i8*, i8** %_3
%_5 = bitcast i8* %_4 to i1 (i8*)*
%_6 = call i1 %_5(i8* %_0)
br i1 %_6, label %if57, label %if58
if57:
%_7 = bitcast i8* %this to i8***
%_8 = load i8 **, i8*** %_7
%_9 = getelementptr i8*, i8** %_8,i32 19
%_10 = load i8*, i8** %_9
%_11 = bitcast i8* %_10 to i1 (i8*,i8*)*
%_12 = load i8*, i8** %node
%_13 = bitcast i8* %_12 to i8***
%_14 = load i8 **, i8*** %_13
%_15 = getelementptr i8*, i8** %_14,i32 4
%_16 = load i8*, i8** %_15
%_17 = bitcast i8* %_16 to i8* (i8*)*
%_18 = call i8* %_17(i8* %_12)
%_19 = call i1 %_11(i8* %this,i8* %_18)
store i1 %_19, i1* %ntb
br label %if59
if58:
store i1 1, i1* %ntb
br label %if59
if59:
%_20 = load i8*, i8** %node
%_21 = bitcast i8* %_20 to i8***
%_22 = load i8 **, i8*** %_21
%_23 = getelementptr i8*, i8** %_22,i32 5
%_24 = load i8*, i8** %_23
%_25 = bitcast i8* %_24 to i32 (i8*)*
%_26 = call i32 %_25(i8* %_20)
call void (i32) @print_int(i32 %_26)
%_27 = load i8*, i8** %node
%_28 = bitcast i8* %_27 to i8***
%_29 = load i8 **, i8*** %_28
%_30 = getelementptr i8*, i8** %_29,i32 7
%_31 = load i8*, i8** %_30
%_32 = bitcast i8* %_31 to i1 (i8*)*
%_33 = call i1 %_32(i8* %_27)
br i1 %_33, label %if60, label %if61
if60:
%_34 = bitcast i8* %this to i8***
%_35 = load i8 **, i8*** %_34
%_36 = getelementptr i8*, i8** %_35,i32 19
%_37 = load i8*, i8** %_36
%_38 = bitcast i8* %_37 to i1 (i8*,i8*)*
%_39 = load i8*, i8** %node
%_40 = bitcast i8* %_39 to i8***
%_41 = load i8 **, i8*** %_40
%_42 = getelementptr i8*, i8** %_41,i32 3
%_43 = load i8*, i8** %_42
%_44 = bitcast i8* %_43 to i8* (i8*)*
%_45 = call i8* %_44(i8* %_39)
%_46 = call i1 %_38(i8* %this,i8* %_45)
store i1 %_46, i1* %ntb
br label %if62
if61:
store i1 1, i1* %ntb
br label %if62
if62:
ret i1 1
}
