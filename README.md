[TOC]

# 编译实验文档

## 一、词法分析

词法分析部分我采用的是大自动机模式，自动机如下：

![词法分析](F:\study\大三上\编译\实验\词法分析.png)

## 二、语法分析

解决左递归问题：
$$
\begin{flalign}
&\ AddExp \rightarrow MulExp \ |\ AddExp\ ('+'\ |\ '−')\ MulExp \tag{1}\\
=>&\ AddExp \rightarrow MulExp\ \{\ ('+'\ |\ '−')\ MulExp\ \}\\
\\
&\ MulExp \rightarrow UnaryExp\ |\ MulExp\ ('*'\ |\ '/'\ |\ '\%')\ UnaryExp \tag{2} \\
=>&\ MulExp \rightarrow UnaryExp\ \{\ ('*'\ |\ '/'\ |\ '\%')\ UnaryExp\ \}\\
\\
&\ LOrExp \rightarrow LAndExp\ |\ LOrExp\ '||'\ LAndExp \tag{3}\\
=>&\ LOrExp \rightarrow LAndExp\ \{\ '||'\ LAndExp\ \}\\
\\
&\ LAndExp \rightarrow EqExp\ |\ LAndExp\ '\&\&'\ EqExp \tag{4}\\
=>&\ LAndExp \rightarrow EqExp\ \{\ '\&\&'\ EqExp\ \}\\
\\
&\ EqExp \rightarrow RelExp\ |\ EqExp\ ('=='\ |\ '!=')\ RelExp \tag{5}\\
=>&\ EqExp \rightarrow RelExp\ \{\ ('=='\ |\ '!=')\ RelExp\ \}\\
\\
&\ RelExp \rightarrow AddExp\ |\ RelExp\ ('<'\ |\ '>'\ |\ '<='\ |\ '>=')\ AddExp \tag{6}\\
=>&\ RelExp \rightarrow AddExp\ \{\ ('<'\ |\ '>'\ |\ '<='\ |\ '>=')\ AddExp\  \}\\
\end{flalign}
$$

