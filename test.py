from tkinter import *
import tkinter as tk
import time

# tkinter 객체 생성
window = Tk()
window.title('약 복용 도우미')
window.geometry('800x600+100+100')

user_id = tk.StringVar()
user_phone = tk.StringVar()

###복약 시각, 횟수, 보호자 연락처에 대한 데이터의 형식은??


# 메인화면
def check_data():
    a=('+82'+(user_id.get()[1:]))
    print(a)
    window.destroy() # 로그인 창을 닫고 메인 화면 생성
    newwin = Tk()
    newwin.title('복약일지')
    newwin.geometry("2000x1200")
    newwin['bg']='white'
    ###user_id.get(), 이름으로 바꿀 부분
    tk.Label(newwin, text=user_id.get() + " 님, ",width=20, height=2, font=('맑은 고딕',35,'bold'),bg='white',
        fg='black').place(relx=0.5,rely=0.25, anchor = "s")
    tk.Label(newwin, text="복 약 일 지",width=10, height=2, font=('맑은 고딕',40,'bold'),
        fg='black').place(relx=0.5,rely=0.45, anchor = "s")
    tk.Button(newwin, text = "복약 시간 확인", command = check_time, width=20, height=2, font=('맑은 고딕',30,'bold'),bg='skyblue',
        fg='white').place(relx=0.5,rely=0.65, anchor = "s")
    tk.Button(newwin, text = "보호자 연락처 확인", command = check_call, width=20, height=2, font=('맑은 고딕',30,'bold'),bg='skyblue',
        fg='white').place(relx=0.5,rely=0.85, anchor = "s")
    check_clock()
    newwin.mainloop()

##시계 위치 어디로??
def check_clock():
    def clock(): # 현재 시간 표시 / 반복
       live_T = time.strftime("%H:%M:%S") # Real Time
       clock_width.config(text=live_T)
       clock_width.after(200, clock) # .after(지연시간{ms}, 실행함수)
    root = Tk()
    root.title("Clock")
    root.geometry("+100+1")
    root.wm_attributes("-topmost", 1) # 창을 항상 상단에 배치 / 0 외 모든 인자 True

    txt_frame = Frame(root)
    txt_frame.pack()

    txt_width = Label(txt_frame, text="현재 시간",font=('맑은 고딕',35))
    txt_width.pack()

    clock_frame = Frame(root)
    clock_frame.pack()

    clock_width = Label(clock_frame, font=("Times",35,"bold"), bg="white", bd=8)
    clock_width.pack()

    clock()
    root.mainloop()

#복약 시간 확인
def check_time():
    medtime = Tk()
    medtime.title('복약 시간 확인')
    medtime.geometry("2000x1200")
    medtime['bg']='white'
    tk.Label(medtime, text="복약 시간 확인",width=20, height=1,relief="raised", font=('맑은 고딕',30,'bold'),bg='white',
        fg='black').place(relx=0.5,rely=0.15, anchor = "s")
    tk.Label(medtime, text="복 약 시 각",width=15, height=1,relief="raised", font=('맑은 고딕',30),bg='skyblue',
        fg='blue').place(relx=0.5,rely=0.25, anchor = "s")
    tk.Label(medtime, text="아침",width=5, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.35,rely=0.35, anchor = "s")
    tk.Label(medtime, text="점심",width=5, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.35,rely=0.45, anchor = "s")
    tk.Label(medtime, text="저녁",width=5, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.35,rely=0.55, anchor = "s")
    ############앱 데이터에서 아침 점심 저녁 각각의 시간값을 받아서 label로 출력
    tk.Label(medtime, text="  ",width=15, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.6,rely=0.35, anchor = "s")
    tk.Label(medtime, text="  ",width=15, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.6,rely=0.45, anchor = "s")
    tk.Label(medtime, text="  ",width=15, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
            fg='black').place(relx=0.6,rely=0.55, anchor = "s")
    #############################################
    tk.Label(medtime, text="복 약 횟 수",width=15, height=1,relief="raised", font=('맑은 고딕',30),bg='skyblue',
        fg='navy').place(relx=0.5,rely=0.65, anchor = "s")

    ###########횟수들의 색깔 조절(if??)
    tk.Label(medtime, text="아침",width=10, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.3,rely=0.75, anchor = "s")
    tk.Label(medtime, text="점심",width=10, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.5,rely=0.75, anchor = "s")
    tk.Label(medtime, text="저녁",width=10, height=1,relief="groove", font=('맑은 고딕',30,'bold'),
        fg='black').place(relx=0.7,rely=0.75, anchor = "s")
    tk.Button(medtime, text = "닫기",font=('맑은 고딕',20,'bold'),fg='black',width=10, command = medtime.destroy).place(relx=0.7,rely=0.9, anchor = "s")


    medtime.mainloop()

def check_call():#앱에서 등록한 보호자 연락처를 보여주는 창
    medcall = Tk()
    medcall.title('보호자/기관 연락처')
    medcall['bg']='white'
    medcall.geometry("2000x1200")
    tk.Label(medcall, text="보호자 연락처 목록",width=20, height=2, font=('맑은 고딕',35,'bold'),bg='white',
        fg='black').place(relx=0.5,rely=0.2, anchor = "s")
    tk.Button(medcall, text = "닫기",font=('맑은 고딕',20,'bold'),fg='black',width=10, command = medtime.destroy).place(relx=0.7,rely=0.9, anchor = "s")

    medcall.mainloop()

# 사용자 이름 입력 (맨 처음 화면)
id=tk.Label(window, text = "전화번호를 입력해주세요(-제외)",width=25, height=1, font=('맑은 고딕',40,'bold'),
    fg='black')

id.place(relx=0.5,rely=0.3, anchor = "s")
tk.Entry(window, textvariable = user_id, font=('맑은 고딕',40,'bold'),bg='white',width=12).place(relx=0.5,rely=0.45, anchor = "s")
tk.Button(window, text = "복약일지 확인하기",font=('맑은 고딕',30,'bold'),bg="skyblue",fg='white',width=20,height=1,relief="raised", command = check_data).place(relx=0.5,rely=0.7, anchor = "s")


window.mainloop()
